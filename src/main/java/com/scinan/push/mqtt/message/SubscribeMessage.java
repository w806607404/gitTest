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
import java.util.ArrayList;
import java.util.List;

import com.scinan.push.mqtt.message.util.FormatUtil;

public class SubscribeMessage extends RetryableMessage {

	private List<String> topics = new ArrayList<String>();
	private List<QoS> topicQoSs = new ArrayList<QoS>();

	// 内部处理用的
	private String clientId;

	public SubscribeMessage(Header header) throws IOException {
		super(header);
	}

	public SubscribeMessage(String topic, QoS topicQos) {
		super(Type.SUBSCRIBE);
		setQos(QoS.AT_LEAST_ONCE);
		topics.add(topic);
		topicQoSs.add(topicQos);
	}

	@Override
	protected int messageLength() {
		int length = 2; // message id length
		for (String topic : topics) {
			length += FormatUtil.toMQttString(topic).length;
			length += 1; // topic QoS
		}
		return length;
	}

	@Override
	protected void writeMessage(OutputStream out) throws IOException {
		super.writeMessage(out);
		DataOutputStream dos = new DataOutputStream(out);
		for (int i = 0; i < topics.size(); i++) {
			dos.writeUTF(topics.get(i));
			dos.write(topicQoSs.get(i).val);
		}
		dos.flush();
	}

	@Override
	protected void readMessage(InputStream in, int msgLength)
			throws IOException {
		DataInputStream dis = new DataInputStream(in);
		int pos = 0;
		if (getQos() != QoS.AT_MOST_ONCE) {
			super.readMessage(in, msgLength);
			pos = pos + 2;
		}
		while (pos < msgLength) {
			String tempTopic = dis.readUTF();
			pos = pos + tempTopic.length() + 2;
			int qosValue = dis.read();
			pos = pos + 1;
			QoS tempQoS = null;

			if (qosValue == 0) {
				tempQoS = QoS.valueOf(0);
			} else if (qosValue == 1) {
				tempQoS = QoS.valueOf(1);
			} else if (qosValue == 2) {
				tempQoS = QoS.valueOf(2);
			} else {
				// exception
			}
			topics.add(tempTopic);
			topicQoSs.add(tempQoS);
		}
	}

	@Override
	public void setQos(QoS qos) {
		if (qos != QoS.AT_LEAST_ONCE) {
			throw new IllegalArgumentException(
					"SUBSCRIBE is always using QoS-level AT LEAST ONCE. Requested level: "
							+ qos);
		}
		super.setQos(qos);
	}

	@Override
	public void setDup(boolean dup) {
		if (dup == true) {
			throw new IllegalArgumentException(
					"SUBSCRIBE can't set the DUP flag.");
		}
		super.setDup(dup);
	}

	@Override
	public void setRetained(boolean retain) {
		throw new UnsupportedOperationException(
				"SUBSCRIBE messages don't use the RETAIN flag");
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	public List<QoS> getTopicQoSs() {
		return topicQoSs;
	}

	public void setTopicQoSs(List<QoS> topicQoSs) {
		this.topicQoSs = topicQoSs;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
