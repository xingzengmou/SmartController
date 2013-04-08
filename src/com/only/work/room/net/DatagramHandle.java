package com.only.work.room.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.only.work.room.debug.Debug;
import com.only.work.room.net.*;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DatagramHandle {
	private static final String TAG = "DatagramHandle";
	private String ip = "192.168.1.127";
	private int port = 3342;
	private OnUDPReceiveFinishListener mOnUDPReceiveFinishListener;
	private String sendType;
	
	private DatagramSocket dSocket = null;
	private InetAddress inetAddress = null;
	
	public DatagramHandle() {
		try {
			dSocket = new DatagramSocket();
			inetAddress = InetAddress.getByName("192.168.1.127");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setIP(String ip) {
		this.ip = ip;
		Log.e(TAG, "IP = " + ip);
		try {
			inetAddress = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void send( final byte[] cmd) {
		if (Debug.debug) {
			String t = "";
			for (byte b: cmd) {
				t += Integer.toHexString((b & 0x00FF)) + " ";
			}
			Log.e(TAG, "ip = " + ip + " port = " + port);
			Log.e(TAG, " will be send: " + t);
		}
		new Thread("") {
			public void run() {
				DatagramPacket p = new DatagramPacket( cmd, cmd.length, inetAddress, port);
				try {
					dSocket.send(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	@SuppressWarnings("finally")
	public void receiver(final String cmdType, final byte[] cmd) {
		if (Debug.debug) {
			String temp = "";
			for (byte b: cmd) {
				temp += Integer.toHexString((b & 0xff));
			}
			Log.e(TAG, " receiver cmd = " + temp);
		}
		new Thread("") {
			byte[] recvBuf = new byte[256];
			public void run() {
				int count = 2;
				Log.e("ControlView", " receiver");
				
				String t = ""; 
				for (byte b: cmd) {
					t += Integer.toHexString((b & 0xff)) + " ";
				}
				Log.e(TAG, " cmdName = " + cmdType + " cmd = " + t);
				
				while ((count --) > 0) {
				DatagramPacket p = new DatagramPacket( cmd, cmd.length, inetAddress, port);
				try {
				dSocket.send(p);
				Log.e("ControlView", " receiver333");
				p = new DatagramPacket(recvBuf, recvBuf.length);
				dSocket.setSoTimeout(200);
				dSocket.receive(p);
				Log.e("ControlView", " receiver5555");
				Log.e(TAG, " cmdType = " + cmdType);
				String temp = ""; 
				for (byte b: recvBuf) {
					temp += Integer.toHexString((b & 0xff)) + " ";
				}
				Log.e(TAG, " cmdName = " + cmdType + " msg = " + temp);
				Thread.sleep(10);
				} catch (Exception e) {
					Log.e(TAG, "  exception");
					e.printStackTrace();
				}
				}
				Log.e("ControlView", "  exception3333333");
				mOnUDPReceiveFinishListener.onUDPReceiveFinish(cmdType, recvBuf);
			}
		}.start();
	}
	
	public void setOnUDPReceivFinishListener(OnUDPReceiveFinishListener listener) {
		mOnUDPReceiveFinishListener = listener;
	}
	
}
