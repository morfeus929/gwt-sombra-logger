package com.sombrainc.gwt.sombra.logger.server;

import javax.servlet.http.HttpServletRequest;

import com.google.gwt.core.server.StackTraceDeobfuscator;
import com.google.gwt.core.shared.SerializableThrowable;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sombrainc.gwt.sombra.logger.client.LogService;

public abstract class AbstractLogServiceServlet extends RemoteServiceServlet implements LogService {

	@Override
	public void logClientException(SerializableThrowable ex, String userAgent, String platform, String moduleName) {
		final HttpServletRequest request = getThreadLocalRequest();
		final String symbolMapsDirectory = getPathToDeployFolder(moduleName, request);
		final String permutationName = request.getHeader(RpcRequestBuilder.STRONG_NAME_HEADER);
		StackTraceDeobfuscator.fromFileSystem(symbolMapsDirectory).deobfuscateStackTrace(ex, permutationName);
		logException(ex, request, userAgent, platform);
	}

	public String getPathToDeployFolder(String moduleName, final HttpServletRequest request) {
		final String symbolMapsDirectory = request.getServletContext().getRealPath("/WEB-INF/deploy/" + moduleName + "/symbolMaps");
		return symbolMapsDirectory;
	}

	protected abstract void logException(SerializableThrowable e, HttpServletRequest request, String userAgent, String platform);

}
