package gt.com.tigo.workeragreementrenewmanagement.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import gt.com.tigo.workeragreementrenewmanagement.services.WorkerReaderConfService;

@Component
public class InitBean {

	@Autowired
	WorkerReaderConfService workerReaderConfService;

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationEventReady() {
		workerReaderConfService.startProcess();
	}
}
