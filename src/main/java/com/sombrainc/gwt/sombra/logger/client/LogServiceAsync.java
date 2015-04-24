package com.sombrainc.gwt.sombra.logger.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LogServiceAsync {

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see com.sombrainc.handler.shared.LogService
	 */
	void logClientException(com.google.gwt.core.shared.SerializableThrowable ex, java.lang.String userAgent, java.lang.String platform,
			java.lang.String moduleName, AsyncCallback<Void> callback);

	/**
	 * Utility class to get the RPC Async interface from client-side code
	 */
	public static final class Util {
		private static LogServiceAsync instance;

		public static final LogServiceAsync getInstance() {
			if (instance == null) {
				instance = (LogServiceAsync) GWT.create(LogService.class);
			}
			return instance;
		}

		private Util() {
			// Utility class should not be instantiated
		}
	}
}
