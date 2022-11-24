package gt.com.tigo.workeragreementrenewmanagement.commons.util;

import org.springframework.stereotype.Service;

import gt.com.tigo.workeragreementrenewmanagement.models.dto.CustomErrorDTO;

/**
 *
 * @author pamartinez
 */

@Service
public class Utilities {

	public CustomErrorDTO error(Integer code, String description) {
		CustomErrorDTO err = new CustomErrorDTO();
		// err.status(code);
		switch (code) {
		case 400:
			err.setErrorCode(400);
			err.setErrorType("MSJ");
			err.setCode("001");
			err.setDescription("Error en mensaje de entrada.");
			break;
		case 401:
			err.setErrorCode(401);
			err.setErrorType("SEG");
			err.setCode("001");
			err.setDescription("Invalid authorization code");
			break;
		case 403:
			err.setErrorCode(403);
			err.setErrorType("NEG");
			err.setCode("003");
			err.setDescription("Request cannot be completed.");
			break;
		case 404:
			err.setErrorCode(404);
			err.setErrorType("MSJ");
			err.setCode("002");
			err.setDescription("Trying to access a resource that do not exists.");
			break;
		case 405:
			err.setErrorCode(405);
			err.setErrorType("MSJ");
			err.setCode("002");
			err.setDescription("promocion esta asignada a un anexo");
			break;
		case 429:
			err.setErrorCode(429);
			err.setDescription("Limit is exceeded.");
			break;
		case 500:
			err.setErrorCode(500);
			err.setDescription("Internal server error.");
			break;
		case 503:
			err.setErrorCode(503);
			err.setErrorType("COM");
			err.setCode("003");
			err.setDescription("No hay comunicacion con el servicio.");
			break;
		case 504:
			err.setErrorCode(504);
			err.setErrorType("COM");
			err.setCode("003");
			err.setDescription("No hay comunicacion con el servicio.");
			break;
		default:
			err.setDescription("Unknow error.");
		}
		if (description.length() == 0) {
			return err;
		} else {
			err.setDescription(description);
			return err;
		}
	}

}
