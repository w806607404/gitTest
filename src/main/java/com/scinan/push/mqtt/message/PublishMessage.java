/*******************************************************************************
 * Copyright 2011 Albin Theander
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.scinan.push.mqtt.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scinan.push.mqtt.message.util.FormatUtil;
import com.scinan.push.mqtt.message.util.Function;

public class PublishMessage extends RetryableMessage {
	 private final static Logger logger = LoggerFactory.getLogger(PublishMessage.class);
		
	private Long creatTime ;
	public Long getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Long creatTime) {
		this.creatTime = creatTime;
	}

	private String topic;
	private byte[] data;

	// 内部处理用的
	private String clientId;

	public PublishMessage(String topic, String msg) throws UnsupportedEncodingException {
		this(topic, msg.getBytes("UTF-8"));
	}

	public PublishMessage(String topic, byte[] data) {
		super(Type.PUBLISH);
		this.topic = topic;
		this.data = data;
	}

	public PublishMessage(Header header) throws IOException {
		super(header);
	}

	@Override
	protected int messageLength() {
		int length = FormatUtil.toMQttString(topic).length;
		length += (getQos() == QoS.AT_MOST_ONCE) ? 0 : 2;
		length += data.length;
		return length;
	}

	@Override
	protected void writeMessage(OutputStream out) throws IOException {
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeUTF(topic);
		dos.flush();
		if (getQos() != QoS.AT_MOST_ONCE) {
			super.writeMessage(out);
		}
		dos.write(data);
		dos.flush();
	}

	@Override
	protected void readMessage(InputStream in, int msgLength) throws IOException {
		int pos = 0;
		DataInputStream dis = new DataInputStream(in);
		topic = dis.readUTF();
		logger.info(Function.bytesToHexString(topic.getBytes()));
		pos += FormatUtil.toMQttString(topic).length;
		if (getQos() != QoS.AT_MOST_ONCE) {
			super.readMessage(in, msgLength);
			pos += 2;
		}
		data = new byte[msgLength - pos];
		dis.read(data);
	}

	public String getTopic() {
		return topic;
	}

	public byte[] getData() {
		return data;
	}

	public String getDataAsString() {
//		return new String(data);
		 return FormatUtil.toString(data);
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String toString() {
		StringBuffer result = new StringBuffer("");
		result.append(getHeader().toString()).append(",");
		result.append("msgLength:").append(messageLength()).append(",");
		if (getQos() != QoS.AT_MOST_ONCE) {
			result.append("messageId:").append(getMessageId()).append(",");
		}
		result.append("topic:").append(topic).append(",");
		try {
			result.append("data:").append(Function.bytesToHexString(getData()))
					.append(",");
		} catch (Exception e) {
		}
		result.append("client id:").append(getClientId());
		return result.toString();
	}

}
