package com.sombrainc.gwt.sombra.logger.client;

import com.google.gwt.core.shared.SerializableThrowable;
import com.google.gwt.user.client.rpc.RemoteService;

public interface LogService extends RemoteService {

	void logClientException(SerializableThrowable ex, String userAgent, String platform, String moduleName);
}
