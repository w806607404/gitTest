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

import com.scinan.push.mqtt.message.util.FormatUtil;

public class ConnectMessage extends Message {

	private static final String PROTOCOL_ID = "MQIsdp";
	private static final byte PROTOCOL_VERSION = 3;
	private static int CONNECT_HEADER_SIZE = 12;

	private String clientId;
	private int keepAlive;
	private String username;
	private String password;
	private boolean cleanSession;
	private String willTopic;
	private String will;
	private QoS willQoS = QoS.AT_MOST_ONCE;
	private boolean retainWill = false;

	// add by locker
	private String client_protocol_id;
	private byte client_protocol_version;

	public ConnectMessage(Header header) throws IOException {
		super(header);
	}

	public ConnectMessage(String clientId, boolean cleanSession, int keepAlive) {
		super(Type.CONNECT);
		if (clientId == null || clientId.length() > 23) {
			throw new IllegalArgumentException("Client id cannot be null and must be at most 23 characters long: " + clientId);
		}
		this.clientId = clientId;
		this.cleanSession = cleanSession;
		this.keepAlive = keepAlive;
	}

	@Override
	protected int messageLength() {
		int payloadSize = FormatUtil.toMQttString(clientId).length;
		payloadSize += FormatUtil.toMQttString(willTopic).length;
		payloadSize += FormatUtil.toMQttString(will).length;
		payloadSize += FormatUtil.toMQttString(username).length;
		payloadSize += FormatUtil.toMQttString(password).length;
		return payloadSize + CONNECT_HEADER_SIZE;
	}

	@Override
	protected void writeMessage(OutputStream out) throws IOException {
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeUTF(PROTOCOL_ID);
		dos.write(PROTOCOL_VERSION);
		int flags = cleanSession ? 2 : 0;
		flags |= (will == null) ? 0 : 0x04;
		flags |= willQoS.val << 3;
		flags |= retainWill ? 0x20 : 0;
		flags |= (password == null) ? 0 : 0x40;
		flags |= (username == null) ? 0 : 0x80;
		dos.write((byte) flags);
		dos.writeChar(keepAlive);

		dos.writeUTF(clientId);
		if (will != null) {
			dos.writeUTF(willTopic);
			dos.writeUTF(will);
		}
		if (username != null) {
			dos.writeUTF(username);
		}
		if (password != null) {
			dos.writeUTF(password);
		}
		dos.flush();
	}

	@Override
	protected void readMessage(InputStream in, int msgLength) throws IOException {
		DataInputStream dis = new DataInputStream(in);
		client_protocol_id = dis.readUTF();
		client_protocol_version = dis.readByte();
		int flags = dis.readByte();
		// 处理这些flag

		int userFlag = flags & 0x80;
		int passwordFlag = flags & 0x40;

		retainWill = (flags & 0x20) == 0x20;

		int willQosValue = (flags & 0x0C) >> 3;

		if (willQosValue == 0) {
			willQoS = QoS.AT_MOST_ONCE;
		} else if (willQosValue == 1) {
			willQoS = QoS.AT_LEAST_ONCE;
		} else if (willQosValue == 2) {
			willQoS = QoS.EXACTLY_ONCE;
		} else {
			throw new UnsupportedOperationException("No support QoS " + willQosValue);
		}
		int willFlag = flags & 0x04;
		cleanSession = (flags & 2) == 2;

		// 读取keep_alive
		keepAlive = dis.readChar();
		clientId = dis.readUTF();

		if (willFlag == 0x04) {
			willTopic = dis.readUTF();
			will = dis.readUTF();
		}
		if (userFlag == 0x80) {
			username = dis.readUTF();
		}
		if (passwordFlag == 0x40) {
			password = dis.readUTF();
		}
	}

	public void setCredentials(String username, String password) {
		this.username = username;
		this.password = password;

	}

	public void setWill(String willTopic, String will) {
		this.willTopic = willTopic;
		this.will = will;
	}

	public void setWill(String willTopic, String will, QoS willQoS, boolean retainWill) {
		this.willTopic = willTopic;
		this.will = will;
		this.willQoS = willQoS;
		this.retainWill = retainWill;

	}

	@Override
	public void setDup(boolean dup) {
		throw new UnsupportedOperationException("CONNECT messages don't use the DUP flag.");
	}

	@Override
	public void setRetained(boolean retain) {
		throw new UnsupportedOperationException("CONNECT messages don't use the RETAIN flag.");
	}

	@Override
	public void setQos(QoS qos) {
		throw new UnsupportedOperationException("CONNECT messages don't use the QoS flags.");
	}

	public String getClient_protocol_id() {
		return client_protocol_id;
	}

	public void setClient_protocol_id(String client_protocol_id) {
		this.client_protocol_id = client_protocol_id;
	}

	public byte getClient_protocol_version() {
		return client_protocol_version;
	}

	public void setClient_protocol_version(byte client_protocol_version) {
		this.client_protocol_version = client_protocol_version;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(int keepAlive) {
		this.keepAlive = keepAlive;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCleanSession() {
		return cleanSession;
	}

	public void setCleanSession(boolean cleanSession) {
		this.cleanSession = cleanSession;
	}

	public String getWillTopic() {
		return willTopic;
	}

	public void setWillTopic(String willTopic) {
		this.willTopic = willTopic;
	}

	public String getWill() {
		return will;
	}

	public void setWill(String will) {
		this.will = will;
	}

	public QoS getWillQoS() {
		return willQoS;
	}

	public void setWillQoS(QoS willQoS) {
		this.willQoS = willQoS;
	}

	public boolean isRetainWill() {
		return retainWill;
	}

	public void setRetainWill(boolean retainWill) {
		this.retainWill = retainWill;
	}

}
