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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.scinan.push.mqtt.message.util.Function;

public abstract class RetryableMessage extends Message {
	
	private int messageId;

	public RetryableMessage(Header header) throws IOException {
		super(header);
	}

	public RetryableMessage(Type type) {
		super(type);
	}
	
	@Override
	protected int messageLength() {
		return 2;
	}
	
	@Override
	protected void writeMessage(OutputStream out) throws IOException {
		byte[] tempArray=null;
		
		for (int i = 0; i < 10; i++) {
			int id = getMessageId();

			tempArray = Function.intToByteArray2(id);
			if (tempArray[1] != 0x1B && tempArray[0] != 0x1B) {
				break;
			}else{
				next1CId();
			}
		}
		
		out.write(tempArray[0]);
		out.write(tempArray[1]);
//		out.write(msb);
//		out.write(lsb);
	}

	@Override
	protected void readMessage(InputStream in, int msgLength) throws IOException {
//		int msgId = in.read() * 0xFF + in.read()+1;
//		logger.info("message id:"+msgId);
		byte[] messageIDArray = new byte[2];
		in.read(messageIDArray);
		int msgId = Function.byteArrayToInt2(messageIDArray);
		
		setMessageId(msgId);
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getMessageId() {
		if (messageId == -1) {
			messageId = nextId();
		}
		return messageId;
	}


}
