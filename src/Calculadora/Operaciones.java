package Calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Operaciones {
	
	public String redondear(String resultado, int indicePunto) {
		BigDecimal resultadoAprox = new BigDecimal(resultado);
		resultadoAprox = resultadoAprox.setScale(5, RoundingMode.HALF_UP);
		return resultadoAprox.toString();
	}
	
	public String calcular(ScriptEngine engine, String historial) {	
		Object resultado = null;
		try {
			resultado = engine.eval(historial);		
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		int indicePunto = resultado.toString().indexOf(".");
		if (indicePunto != -1 && resultado.toString().length() - indicePunto > 5) {
			resultado = redondear(resultado.toString(), indicePunto);
		}
		return resultado.toString();
	}
}
