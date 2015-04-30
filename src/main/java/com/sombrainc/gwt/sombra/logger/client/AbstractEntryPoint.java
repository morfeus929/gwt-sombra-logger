package com.sombrainc.gwt.sombra.logger.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.SerializableThrowable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public abstract class AbstractEntryPoint implements EntryPoint {

	private LogServiceAsync logService = null;

	public AbstractEntryPoint(String url) {
		logService = GWT.create(LogService.class);
		((ServiceDefTarget) logService).setServiceEntryPoint(url);
	}

	@Override
	public final void onModuleLoad() {
		setExceptionHandler();

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				onModuleLoad2();
			}
		});
	}

	private void setExceptionHandler() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {

			@Override
			public void onUncaughtException(Throwable e) {
				logService.logClientException(SerializableThrowable.fromThrowable(e), Window.Navigator.getUserAgent(),
						Window.Navigator.getPlatform(), GWT.getModuleName(), new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {

							}

							@Override
							public void onFailure(Throwable caught) {

							}
						});
			}
		});
	}

	/**
	 * The entry point method, called automatically by loading a module that
	 * declares an implementing class as an entry point. Must be used instead
	 * <code>onModuleLoad()</code>.
	 */
	public abstract void onModuleLoad2();
}
