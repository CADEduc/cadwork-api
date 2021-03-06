package br.com.imd.cadwork.util;

import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.google.gson.Gson;

public class ValidatorUtil {
	/**
	 * Gera Json com os errors obtidos 
	 * @param allErrors Lis<ObjectError> - lista de objeto com todos os errors
	 * @return String - Json com os errors
	 */
	public static String gerarErrorsInJson(List<ObjectError> allErrors) {

		StringBuilder message = new StringBuilder();
		message.append("{Errors:[");
		for (ObjectError error : allErrors) {
			message.append("{");
			message.append(((FieldError) error).getField().concat(": "+ error.getDefaultMessage()));
			message.append("}");
			message.append(",");
		}
		message.append("]");

		String errors = message.toString().replace("},]", "}]}");

		return new Gson().toJson(errors);
	}
	/**
	 * Verifica se um objeto está vazio
	 * @param o Object - um objeto qualquer
	 * @return boolean - se o objeto está vazio ou não
	 */
	public static boolean isEmpty(Object o) {
		//Validar de forma genérica quando um objeto está ou não válido
		return true;
	}
	
	
	
	/* 
	 * http://www.frameworksystem.com/calculando-a-distancia-entre-2-pontos-latitude-e-longitude-em-java/
	 * 
	 * Calculate distance between two points in latitude 
	and longitude taking into account height difference. 
	If you are not interested in height difference pass 0.0.
	 Uses Haversine method as its base. lat1, lon1 Start point 
	lat2, lon2 End point el1 Start altitude in meters el2 End altitude 
	in meters */

	public double distancia(double lat1, double lat2, double lon1, double lon2) {
		final double el1 = 0.0;
		final double el2 = 0.0;
	    final int R = 6371; // Radius of the earth

	    Double latDistance = deg2rad(lat2 - lat1);
	    Double lonDistance = deg2rad(lon2 - lon1);
	    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;
	    distance = Math.pow(distance, 2) + Math.pow(height, 2);
	    return Math.sqrt(distance);
	}

	private double deg2rad(double deg) {
	    return (deg * Math.PI / 180.0);
	}

}
