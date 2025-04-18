package Calculadora;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Calculadora extends JFrame {

	private JPanel contentPane;
	private JTextField historial;
	private JTextField cifra;
	
	ScriptEngineManager manager = new ScriptEngineManager();
	ScriptEngine engine = manager.getEngineByName("js");
	
	Operaciones operaciones = new Operaciones();
	
	public void entrarOperacion(String comando) {
		String operadores = "+-*/";
		if (cifra.getText().startsWith("= ")) {
			historial.setText(cifra.getText().substring(2) + comando);
		} else if (!historial.getText().isEmpty() && operadores.indexOf(historial.getText().substring(historial.getText().length() - 1)) != -1) {	
			if (cifra.getText().matches("0")) {
				historial.setText(historial.getText().substring(0, historial.getText().length() - 1) + comando);
			} else {
				historial.setText(historial.getText() + cifra.getText());	
				historial.setText(operaciones.calcular(engine, historial.getText()) + comando);
			}
		} else if (cifra.getText().endsWith(".")) {
			historial.setText(historial.getText() + cifra.getText() + "0" + comando);
		} else {
			historial.setText(historial.getText() + cifra.getText() + comando);	
		}
		cifra.setText("0");
	}
	
	public Calculadora() {
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Calculadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		historial = new JTextField();
		historial.setFont(new Font("Arial", Font.PLAIN, 15));
		historial.setHorizontalAlignment(SwingConstants.RIGHT);
		historial.setEditable(false);
		historial.setBounds(28, 13, 174, 25);
		contentPane.add(historial);
		historial.setColumns(10);
		
		cifra = new JTextField();
		cifra.setFont(new Font("Arial", Font.PLAIN, 15));
		cifra.setHorizontalAlignment(SwingConstants.RIGHT);
		cifra.setEditable(false);
		cifra.setColumns(10);
		cifra.setBounds(28, 51, 174, 25);
		contentPane.add(cifra);
		cifra.setText("0");
		
		ActionListener botonesDigitos = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (cifra.getText().startsWith("-0") && !cifra.getText().contains(".")) {
					cifra.setText("-" + event.getActionCommand());
				} else if (cifra.getText().matches("0")) {
					cifra.setText(event.getActionCommand());
				} else {
					cifra.setText(cifra.getText() + event.getActionCommand());
				}
			}
		};
		
		ActionListener botonesOperaciones = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				entrarOperacion(event.getActionCommand());
			}
		};
		
		JButton boton0 = new JButton("0");	
		boton0.setFont(new Font("Arial", Font.PLAIN, 15));
		boton0.setBounds(90, 219, 50, 25);
		contentPane.add(boton0);
		boton0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!cifra.getText().startsWith("-0") || cifra.getText().contains(".")) {
					if (cifra.getText().matches("0")) {
						cifra.setText(event.getActionCommand());
					} else {
						cifra.setText(cifra.getText() + event.getActionCommand());
					}
				}
			}
		});
			
		
		JButton boton1 = new JButton("1");
		boton1.setFont(new Font("Arial", Font.PLAIN, 15));
		boton1.setBounds(28, 181, 50, 25);
		contentPane.add(boton1);
		boton1.addActionListener(botonesDigitos);
		
		JButton boton2 = new JButton("2");
		boton2.setFont(new Font("Arial", Font.PLAIN, 15));
		boton2.setBounds(90, 181, 50, 25);
		contentPane.add(boton2);
		boton2.addActionListener(botonesDigitos);	
		
		JButton boton3 = new JButton("3");
		boton3.setFont(new Font("Arial", Font.PLAIN, 15));
		boton3.setBounds(152, 181, 50, 25);
		contentPane.add(boton3);
		boton3.addActionListener(botonesDigitos);
		
		JButton boton4 = new JButton("4");
		boton4.setFont(new Font("Arial", Font.PLAIN, 15));
		boton4.setBounds(28, 143, 50, 25);
		contentPane.add(boton4);
		boton4.addActionListener(botonesDigitos);
		
		JButton boton5 = new JButton("5");
		boton5.setFont(new Font("Arial", Font.PLAIN, 15));
		boton5.setBounds(90, 143, 50, 25);
		contentPane.add(boton5);
		boton5.addActionListener(botonesDigitos);
		
		JButton boton6 = new JButton("6");
		boton6.setFont(new Font("Arial", Font.PLAIN, 15));
		boton6.setBounds(152, 143, 50, 25);
		contentPane.add(boton6);
		boton6.addActionListener(botonesDigitos);
		
		JButton boton7 = new JButton("7");			
		boton7.setFont(new Font("Arial", Font.PLAIN, 15));
		boton7.setBounds(28, 105, 50, 25);
		contentPane.add(boton7);
		boton7.addActionListener(botonesDigitos);
		
		JButton boton8 = new JButton("8");
		boton8.setFont(new Font("Arial", Font.PLAIN, 15));
		boton8.setBounds(90, 105, 50, 25);
		contentPane.add(boton8);
		boton8.addActionListener(botonesDigitos);
		
		JButton boton9 = new JButton("9");
		boton9.setFont(new Font("Arial", Font.PLAIN, 15));
		boton9.setBounds(152, 105, 50, 25);
		contentPane.add(boton9);
		boton9.addActionListener(botonesDigitos);
		
		JButton botonPunto = new JButton(".");
		botonPunto.setFont(new Font("Arial", Font.BOLD, 15));
		botonPunto.setBounds(28, 219, 50, 25);
		contentPane.add(botonPunto);
		botonPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (cifra.getText().indexOf(".") == -1) {
					if (cifra.getText().length() == 0 || cifra.getText().matches("-")) {
						cifra.setText(cifra.getText() + "0.");
					} else {
						cifra.setText(cifra.getText() + ".");
					}				
				}
			}
		});
		
		JButton botonSuma = new JButton("+");
		botonSuma.setFont(new Font("Arial", Font.PLAIN, 15));
		botonSuma.setBounds(214, 105, 50, 25);
		contentPane.add(botonSuma);
		botonSuma.addActionListener(botonesOperaciones);
		
		JButton botonX = new JButton("*");
		botonX.setFont(new Font("Arial", Font.PLAIN, 15));
		botonX.setBounds(214, 181, 50, 25);
		contentPane.add(botonX);
		botonX.addActionListener(botonesOperaciones);
		
		JButton botonResta = new JButton("-");
		botonResta.setFont(new Font("Arial", Font.PLAIN, 15));
		botonResta.setBounds(214, 143, 50, 25);
		contentPane.add(botonResta);
		botonResta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (cifra.getText().matches("0") && historial.getText().isEmpty()) {
					cifra.setText(event.getActionCommand());
				} else {
					entrarOperacion(event.getActionCommand());
				}
			}
		});
		
		JButton botonDivision = new JButton("/");
		botonDivision.setFont(new Font("Arial", Font.PLAIN, 15));
		botonDivision.setBounds(214, 219, 50, 25);
		contentPane.add(botonDivision);
		botonDivision.addActionListener(botonesOperaciones);
		
		JButton botonIgual = new JButton("=");
		botonIgual.setFont(new Font("Arial", Font.PLAIN, 15));
		botonIgual.setBounds(152, 219, 50, 25);
		contentPane.add(botonIgual);
		botonIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (historial.getText().length() != 0 && cifra.getText().length() != 0) {
					historial.setText(historial.getText() + cifra.getText());
					cifra.setText("= " + operaciones.calcular(engine, historial.getText()));
				}				
			}
		});
		
		JButton botonLimpiar = new JButton("C");
		botonLimpiar.setFont(new Font("Arial", Font.PLAIN, 15));
		botonLimpiar.setBounds(214, 13, 50, 25);
		contentPane.add(botonLimpiar);
		botonLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cifra.setText("0");
				historial.setText("");
			}
		});
		
		JButton botonBorrar = new JButton("<");
		botonBorrar.setFont(new Font("Arial", Font.PLAIN, 15));
		botonBorrar.setBounds(214, 51, 50, 25);
		contentPane.add(botonBorrar);
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (cifra.getText().length() == 1) {
					cifra.setText("0");
				} else if (cifra.getText().length() > 0) {
					cifra.setText(cifra.getText().substring(0, cifra.getText().length() - 1));
				}			
			}
		});
	}
}
