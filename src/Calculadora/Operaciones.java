package Calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Operaciones {
	
	ScriptEngineManager manager = new ScriptEngineManager();
	ScriptEngine engine = manager.getEngineByName("js");
	
	// Método para redondear el resultado usando la clase BigDecimal
	public String redondear(String resultado) {
		BigDecimal resultadoAprox = new BigDecimal(resultado);
		resultadoAprox = resultadoAprox.setScale(5, RoundingMode.HALF_UP);
		return resultadoAprox.toString();
	}
	
	// Método para calcular el resultado a través de una String utilizando el propio motor de java
	public String calcular(String historial) {	
		String valor = "";
		try {
			Object resultado = engine.eval(historial);	
			valor = resultado.toString();
			if (!valor.equals("Infinity") && !valor.equals("NaN")) { // Si el resultado no es infinito y no es sin solución
				int indicePunto = valor.indexOf(".");
				if (indicePunto != -1 && valor.length() - indicePunto > 5) { // Limitar a 5 cifras decimales
					valor = redondear(valor);
				}
			} else {
				valor = "";
			}	
		} catch (ScriptException e) {
			e.printStackTrace();
		}	
		return valor;
	}
	
	
}
