package Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import Controle.ControleBasico;
import Controle.ControleGeral;
import Negocio.Bairro;
import Util.Diversos;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.ComponentOrientation;

import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class JFrmBairro extends JFrame {

	private JPanel jContentPane;
	private JTextField jTxtCodigo;
	private JTextField jTxtDescricao;
    private JButton jBtnIncluir;
    private JButton jBtnAlterar;
    private JButton jBtnExcluir;
    private final String titulo;
    private JTextArea jTxtARelatorio;
    private JButton jBtnRelatorio;
    private JScrollPane jScrlPRelatorio;
    private List<Object> listaB;
    private ControleBasico cB;
    
    

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	 	EventQueue.invokeLater(new Runnable() {
	  		public void run() {
				try {
					JFrmBairro frame = new JFrmBairro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
			      }
	      }
    	});
	}

	/**
	 * Create the frame.
	 */
	public JFrmBairro() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//carregaLista();
		        limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jTxtDescricao.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar bairros";
		this.cB = new ControleGeral(2);
		setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		setTitle("Cadastrar bairros");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 698, 339);
		jContentPane = new JPanel();
		jContentPane.setForeground(new Color(30, 144, 255));
		jContentPane.setBackground(Color.CYAN);
		jContentPane.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JPanel jPnlBotoes = new JPanel();
		jPnlBotoes.setBackground(Color.CYAN);
		jPnlBotoes.setBounds(38, 95, 625, 65);
		jPnlBotoes.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jPnlBotoes);
		jPnlBotoes.setLayout(null);
		
		jBtnIncluir = new JButton("Incluir");
		jBtnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarDados('I');			
			}
		});
		jBtnIncluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnIncluir.setBounds(12, 23, 84, 25);
		jPnlBotoes.add(jBtnIncluir);
		
		JButton jBtnLimpar = new JButton("Limpar");
		jBtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		jBtnLimpar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnLimpar.setBounds(415, 23, 84, 25);
		jPnlBotoes.add(jBtnLimpar);
		
		JButton jBtnRetornar = new JButton("Retornar");
		jBtnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja finalisar", titulo))
					dispose();
			}
		});
		jBtnRetornar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRetornar.setBounds(509, 23, 97, 25);
		jPnlBotoes.add(jBtnRetornar);
		
		jBtnAlterar = new JButton("Alterar");
		jBtnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarDados('A');
			}
		});
		jBtnAlterar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnAlterar.setBounds(106, 23, 84, 25);
		jPnlBotoes.add(jBtnAlterar);
		
		jBtnExcluir = new JButton("Excluir");
		jBtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Diversos.confirmar("Deseja excluir", titulo))
					cadastrarDados('E');
			}
		});
		jBtnExcluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnExcluir.setBounds(200, 23, 84, 25);
		jPnlBotoes.add(jBtnExcluir);
		
		jBtnRelatorio = new JButton("Relat\u00F3rio");
		jBtnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  relatorioGeral();	
			}
		});
		jBtnRelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRelatorio.setBounds(294, 23, 111, 25);
		jPnlBotoes.add(jBtnRelatorio);
		
		JLabel jLblCodigo = new JLabel("Codigo");
		jLblCodigo.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCodigo.setBounds(23, 28, 94, 15);
		jLblCodigo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.add(jLblCodigo);
		
		jTxtCodigo = new JTextField();
		jTxtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				  pesquisa();
			}
			
		});
		jTxtCodigo.setBounds(122, 23, 81, 25);
		jTxtCodigo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtCodigo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jTxtCodigo);
		jTxtCodigo.setColumns(10);
		
		JLabel jLblNome = new JLabel("Descri\u00E7\u00E3o");
		jLblNome.setBounds(209, 28, 85, 15);
		jLblNome.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		jContentPane.add(jLblNome);
		
		jTxtDescricao = new JTextField();
		jTxtDescricao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtDescricao.setText(jTxtDescricao.getText().toUpperCase());
			}
		});
		jTxtDescricao.setBounds(304, 23, 346, 25);
		jTxtDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtDescricao.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtDescricao.setColumns(10);
		jTxtDescricao.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jTxtDescricao);
		
		jScrlPRelatorio = new JScrollPane();
		jScrlPRelatorio.setBounds(25, 192, 638, 88);
		jContentPane.add(jScrlPRelatorio);
		
		jTxtARelatorio = new JTextArea();
		jScrlPRelatorio.setViewportView(jTxtARelatorio);
		jTxtARelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtARelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		 setResizable(false);
		 setLocationRelativeTo(null); //centraliza o formulário
		 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 13)));
             UIManager.put("OptionPane.messageForeground", Color.BLUE);
         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmBairro.class.getName()).log(Level.SEVERE, null, ex);
          } 

	}
	
	private void limpar() {
		 JTextField txt[] = {jTxtCodigo, jTxtDescricao};
	     for (JTextField t : txt)
	            t.setText("");
	     jTxtARelatorio.setText("");
	     jTxtCodigo.setEditable(true);
	     JButton jBtn[] = {jBtnAlterar, jBtnExcluir, jBtnIncluir};
	        for (JButton btn : jBtn) 
	            btn.setEnabled(false);    
	     jTxtDescricao.requestFocusInWindow();
	}
	
	private void relatorioGeral() {
        listaB = cB.lista();
        String resp = "";
        for (Object o : listaB) {
            Bairro b = (Bairro) o;
            resp += b.relatorio();
        }
        jTxtARelatorio.setText(!resp.isEmpty() ? resp : "Inexstência de dados");
    }
	
	private void carregaObjetos(Bairro b) {
		jTxtCodigo.setText(String.valueOf(b.getCodigo()));
        jTxtDescricao.setText(b.getDescricao());  
	}
	
	 private void cadastrarDados(char opcao) {
         String resp = "";
         if(jTxtCodigo.getText().isEmpty() || jTxtDescricao.getText().isEmpty())
        	 resp = "Favor digitar os dados";
         else {
                 Bairro b = new Bairro();
                 b.setCodigo(Integer.parseInt(jTxtCodigo.getText()));
                 b.setDescricao(jTxtDescricao.getText());              
                 if (!cB.setManipular(b, opcao)) {
                    resp = "Problemas ao " + 
                    (opcao == 'A' ? "atualizar" : opcao == 'E' ?  "remover" : " inserir") +
                    " os dados do bairro " + b.getDescricao();
                     jBtnAlterar.setEnabled(false);
                     jBtnExcluir.setEnabled(false);
                    jBtnIncluir.setEnabled(false);
                 }   
                 else {
            	      resp = "O bairrro " +  b.getDescricao();
                       switch(opcao) {
                            case 'A' :  resp += "\nFoi atualizado ";
                                             break;
                            case 'E' :  resp  +=  "\nFoi removido ";
                                             limpar();
                                             jBtnAlterar.setEnabled(false);
                                             jBtnExcluir.setEnabled(false);
                                             break;
                            case 'I' :  resp +=  "\nFoi inserido ";
                                            jBtnAlterar.setEnabled(true);
                                            jBtnExcluir.setEnabled(true);
                                           jBtnIncluir.setEnabled(false);                  
                        }
                      resp += " com sucesso";
                  
                 }                  
            }
            Diversos.mostrarDados(resp, titulo, (resp.charAt(0) != 'F' && 
            		resp.charAt(0) != 'P'));
    }
	 
	private void pesquisa() {
		int codigo;
        Bairro b;
        if (!Diversos.testaNum(jTxtCodigo.getText(), titulo))
            jTxtCodigo.setText(""); // converter texto para numero
        else if (!Diversos.intervalo(Integer.parseInt(jTxtCodigo.getText()), 0 ,0 , titulo))
                 jTxtCodigo.setText(""); //testar se é maior que zero
             else {
                     codigo = Integer.parseInt(jTxtCodigo.getText());
                     Object o = cB.getBusca(codigo, 0);
                   if (o == null) {
                      jBtnIncluir.setEnabled(true);
                      Diversos.mostrarDados("Bairro  " + codigo + " inexistente", titulo, true);
                   }    
                   else {
                           b = (Bairro) o;
                           carregaObjetos(b); 
                           jBtnAlterar.setEnabled(true);
                           jBtnExcluir.setEnabled(true);
                  } 
                  jTxtCodigo.setEditable(false);
            }
	}
}
