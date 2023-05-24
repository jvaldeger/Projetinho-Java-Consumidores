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
import Negocio.Consumidor;
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
import javax.swing.ImageIcon;

public class JFrmConsumidor extends JFrame {

	private JPanel jContentPane;
	private JTextField jTxtNumero;
	private JTextField jTxtNome;
	private JComboBox<String> jCmbBairro;
	private JRadioButton jRdbResidencial;
	private JRadioButton jRdbIndustrial;
	private JRadioButton jRdbComercial;
	private JFormattedTextField jFtdTxtDataV;
	private JTextField jTxtPrecoKwh;
	private JCheckBox jChkSim;
	private JLabel jLblPrecoF;
    private JButton jBtnIncluir;
    private JButton jBtnAlterar;
    private JButton jBtnExcluir;
    private JSpinner jSpnQuant ;
    private final ButtonGroup jButtonGroup = new ButtonGroup();
    private JTextArea jTxtARelatorio;
    private JButton jBtnRelatorio;
    private JTextField jTxtQuant;
    private JLabel jLblCodigo;
    
    private final String titulo;
    private ControleBasico cB, cC;  
    private List<Object> listaB, listaC;
   
    

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	 	EventQueue.invokeLater(new Runnable() {
	  		public void run() {
				try {
					JFrmConsumidor frame = new JFrmConsumidor();
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
	public JFrmConsumidor() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				carregaLista();
		        limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jTxtNome.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar consumidores";
		this.cB = new ControleGeral(2);
		this.cC = new ControleGeral(1);
		setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		setTitle("Cadastrar consumidores");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 768, 466);
		jContentPane = new JPanel();
		jContentPane.setForeground(new Color(30, 144, 255));
		jContentPane.setBackground(Color.CYAN);
		jContentPane.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JPanel jPnlBotoes = new JPanel();
		jPnlBotoes.setBackground(Color.CYAN);
		jPnlBotoes.setBounds(23, 245, 696, 65);
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
				if (Diversos.confirmar("Deseja retornar", titulo))
					dispose();
			}
		});
		jBtnRetornar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRetornar.setBounds(509, 23, 106, 25);
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
		
		JButton jBtnBairro = new JButton("");
		jBtnBairro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrmBairro frame = new JFrmBairro();
				frame.setVisible(true);
			}
		});
		jBtnBairro.setIcon(new ImageIcon("C:\\Users\\aluno.info\\workspace\\ConsumidorBairro\\Imagem\\search.png"));
		jBtnBairro.setToolTipText("Bairro");
		jBtnBairro.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnBairro.setBounds(624, 23, 57, 25);
		jPnlBotoes.add(jBtnBairro);
		
		JLabel jLblNumero = new JLabel("N\u00FAmero");
		jLblNumero.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		jLblNumero.setBounds(23, 28, 94, 15);
		jLblNumero.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.add(jLblNumero);
		
		jTxtNumero = new JTextField();
		jTxtNumero.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				  pesquisa();
			}
			
		});
		jTxtNumero.setBounds(122, 23, 81, 25);
		jTxtNumero.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtNumero.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtNumero.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jTxtNumero);
		jTxtNumero.setColumns(10);
		
		JLabel jLblNome = new JLabel("Nome");
		jLblNome.setBounds(265, 28, 62, 15);
		jLblNome.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblNome.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jLblNome);
		
		jTxtNome = new JTextField();
		jTxtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtNome.setText(jTxtNome.getText().toUpperCase());
			}
		});
		jTxtNome.setBounds(349, 23, 368, 25);
		jTxtNome.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtNome.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtNome.setColumns(10);
		jTxtNome.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jTxtNome);
		
		JPanel jPnlImovel = new JPanel();
		jPnlImovel.setBackground(Color.CYAN);
		jPnlImovel.setFont
		(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
	//	jPnlImovel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Tipo de Im\u00F3vel", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
	   jPnlImovel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Tipo de Im\u00F3vel", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13), null));
	 
		jPnlImovel.setBounds(79, 106, 476, 65);
	//	jPnlImovel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de Im\u00F3vel", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
	    jContentPane.add(jPnlImovel);
		jPnlImovel.setLayout(null);
				
		jRdbComercial = new JRadioButton("Comercial");
		jButtonGroup.add(jRdbComercial);
		jRdbComercial.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jRdbComercial.setBackground(Color.CYAN);
		jRdbComercial.setForeground(Color.BLACK);
		jRdbComercial.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jRdbComercial.setBounds(28, 25, 109, 23);
		jPnlImovel.add(jRdbComercial);
		
		jRdbIndustrial = new JRadioButton("Industrial");
		jButtonGroup.add(jRdbIndustrial);
		jRdbIndustrial.setForeground(Color.BLACK);
		jRdbIndustrial.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jRdbIndustrial.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jRdbIndustrial.setBackground(Color.CYAN);
		jRdbIndustrial.setBounds(190, 25, 109, 23);
		jPnlImovel.add(jRdbIndustrial);
		
		jRdbResidencial = new JRadioButton("Residencial");
		jButtonGroup.add(jRdbResidencial);
		jRdbResidencial.setForeground(Color.BLACK);
		jRdbResidencial.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jRdbResidencial.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jRdbResidencial.setBackground(Color.CYAN);
		jRdbResidencial.setBounds(342, 25, 109, 23);
		jPnlImovel.add(jRdbResidencial);
		
		JScrollPane jScrlPRelatorio = new JScrollPane();
		jScrlPRelatorio.setBounds(23, 321, 696, 90);
		jContentPane.add(jScrlPRelatorio);
		
		jTxtARelatorio = new JTextArea();
		jTxtARelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtARelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jScrlPRelatorio.setViewportView(jTxtARelatorio);
		
		JLabel label = new JLabel("Bairro");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		label.setBounds(9, 69, 57, 25);
		jContentPane.add(label);
		
		jCmbBairro = new JComboBox<String>();
		jCmbBairro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionaChave();
			}
		});
		jCmbBairro.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jCmbBairro.setBorder(new LineBorder(Color.BLACK, 2, true));
		jCmbBairro.setBackground(Color.CYAN);
		jCmbBairro.setBounds(64, 70, 140, 25);
		jContentPane.add(jCmbBairro);
		
		jFtdTxtDataV = new JFormattedTextField();
		jFtdTxtDataV.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jFtdTxtDataV.setHorizontalAlignment(SwingConstants.CENTER);
		jFtdTxtDataV.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jFtdTxtDataV.setBounds(605, 98, 91, 25);
		jContentPane.add(jFtdTxtDataV);
		jFtdTxtDataV.setFormatterFactory(Diversos.FormatoMascara(titulo, 0));
		
		JLabel jLblDataV = new JLabel("Data de vencimento");
		jLblDataV.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblDataV.setHorizontalAlignment(SwingConstants.CENTER);
		jLblDataV.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblDataV.setBounds(569, 67, 163, 25);
		jContentPane.add(jLblDataV);
		
		JLabel jLblPrecoK = new JLabel("Pre\u00E7o do Kwh R$");
		jLblPrecoK.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblPrecoK.setHorizontalAlignment(SwingConstants.CENTER);
		jLblPrecoK.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblPrecoK.setBounds(33, 192, 140, 21);
		jContentPane.add(jLblPrecoK);
		
		jTxtPrecoKwh = new JTextField();
		jTxtPrecoKwh.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtPrecoKwh.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtPrecoKwh.setColumns(10);
		jTxtPrecoKwh.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtPrecoKwh.setBounds(179, 190, 81, 25);
		jContentPane.add(jTxtPrecoKwh);
		
		JPanel JpnlAtivo = new JPanel();
		JpnlAtivo.setBackground(Color.CYAN);
		JpnlAtivo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Em Atraso", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13), null));
		JpnlAtivo.setBounds(602, 148, 102, 65);
		jContentPane.add(JpnlAtivo);
		
		jChkSim = new JCheckBox("Sim");
		jChkSim.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jChkSim.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jChkSim.setBackground(Color.CYAN);
		JpnlAtivo.add(jChkSim);
		
		JLabel jLblQuantKwh = new JLabel("Quantidade em Kwh");
		jLblQuantKwh.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblQuantKwh.setHorizontalAlignment(SwingConstants.CENTER);
		jLblQuantKwh.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblQuantKwh.setBounds(345, 71, 155, 21);
		jContentPane.add(jLblQuantKwh);
		
		jLblPrecoF = new JLabel("Preco a pagar de");
		jLblPrecoF.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblPrecoF.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblPrecoF.setHorizontalAlignment(SwingConstants.CENTER);
		jLblPrecoF.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblPrecoF.setBounds(302, 192, 250, 21);
		jContentPane.add(jLblPrecoF);
		
		jTxtQuant = new JTextField();
		jTxtQuant.setText("0");
		jTxtQuant.setForeground(new Color(0, 0, 0));
		jTxtQuant.setBounds(522, 67, 33, 24);
		jContentPane.add(jTxtQuant);
		jTxtQuant.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtQuant.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtQuant.addKeyListener(new KeyAdapter() {
		});
		jTxtQuant.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jTxtQuant.setColumns(10);
		
		jSpnQuant = new JSpinner();
		jSpnQuant.setForeground(new Color(0, 0, 0));
		jSpnQuant.setBounds(506, 67, 16, 24);
		jContentPane.add(jSpnQuant);
		jSpnQuant.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		jSpnQuant.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent arg0) {
		 		jTxtQuant.setText(String.valueOf(jSpnQuant.getValue())); 
		 	}
		});
		jSpnQuant.addKeyListener(new KeyAdapter() {
		 	
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		
		 	}
		 });
		 jSpnQuant.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jSpnQuant.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		 jSpnQuant.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		 jSpnQuant.setBackground(Color.CYAN);
		 
		 jLblCodigo = new JLabel("");
		 jLblCodigo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		 jLblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		 jLblCodigo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jLblCodigo.setBounds(280, 69, 33, 26);
		 jContentPane.add(jLblCodigo);
		 
		 JLabel lblCdigo = new JLabel("C\u00F3digo");
		 lblCdigo.setHorizontalAlignment(SwingConstants.CENTER);
		 lblCdigo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 lblCdigo.setBounds(207, 69, 68, 25);
		 jContentPane.add(lblCdigo);
		 setResizable(false);
		 setLocationRelativeTo(null); //centraliza o formulário
		 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 13)));
             UIManager.put("OptionPane.messageForeground", Color.BLUE);
         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmConsumidor.class.getName()).log(Level.SEVERE, null, ex);
          } 

	}
	
	private void limpar() {
		 JTextField txt[] = {jTxtNumero, jTxtNome, jTxtPrecoKwh};
	     for (JTextField t : txt)
	            t.setText("");
	     jCmbBairro.setSelectedIndex(-1);
	     jButtonGroup.clearSelection();
	     jFtdTxtDataV.setText("");
	     jSpnQuant.setValue(0);
	     jTxtQuant.setText("0");
	     jChkSim.setSelected(false);
	     jLblPrecoF.setText("Preço a pagar de ");
	     jTxtARelatorio.setText("");
	     jTxtNumero.setEditable(true);
	     JButton jBtn[] = {jBtnAlterar, jBtnExcluir, jBtnIncluir};
	        for (JButton btn : jBtn) 
	            btn.setEnabled(false);    
	     jTxtNome.requestFocusInWindow();
	}
	
	private void relatorioGeral() {
        listaC = cC.lista();
        String resp = "";
        for (Object o : listaC) {
            Consumidor c = (Consumidor) o;
            resp += c.relatorio();
        }
        jTxtARelatorio.setText(!resp.isEmpty() ? resp : "Inexstência de dados");
    }
	
	private void carregaLista() {
			 int posi = jCmbBairro.getSelectedIndex(); //guarda a posição corrente
		       listaB = cB.lista();
		       jCmbBairro.removeAllItems();
		       for (Object o : listaB) {
		            Bairro b = (Bairro) o;
		            jCmbBairro.addItem(b.getDescricao());
		        }
		         if (posi > -1) 
		              jCmbBairro.setSelectedIndex(posi);
		         else {
		                jLblCodigo.setText("");
		               jCmbBairro.setSelectedIndex(-1);
		         }
	  }     
	
	private void carregaObjetos(Consumidor c) {
		jTxtNumero.setText(String.valueOf(c.getNumero()));
        jTxtNome.setText(c.getNome());
        jLblCodigo.setText(String.valueOf(c.getBairro().getCodigo()));
        jCmbBairro.setSelectedItem(c.getBairro().getDescricao());
        //jCmbBairro.setSelectedItem(c.getBairro());
        JRadioButton rdb[] = {jRdbComercial, jRdbIndustrial, jRdbResidencial};
	     for (JRadioButton r : rdb)
	            r.setSelected(c.getTipo()== 'C' ? true : c.getTipo()== 'I' ? true : true);
      
      /*  switch(c.getTipo()) {
           case 'C' : jRdbComercial.setSelected(true);
                      break;
           case 'I' : jRdbIndustrial.setSelected(true);
                      break;
           case 'R' : jRdbResidencial.setSelected(true);
        }*/
        jFtdTxtDataV.setText(c.getDataV());
        jSpnQuant.setValue(c.getQuantKwh());
        jTxtQuant.setText(String.valueOf(jSpnQuant.getValue())); 
        jChkSim.setSelected(c.isAtraso());   
        jTxtPrecoKwh.setText(Diversos.doisDigitos(1).format(c.getPrecoKwh()));
        jLblPrecoF.setText("Preço a pagar de " + 
                 Diversos.doisDigitos(1).format(c.precoReal()));        
	}
	
	 private void cadastrarDados(char opcao) {
         String resp = "";
         if(jTxtNumero.getText().isEmpty() || jTxtNome.getText().isEmpty() || jCmbBairro.getSelectedIndex() < 0 ||
        		 jTxtPrecoKwh.getText().isEmpty() ||
        		 (!jRdbComercial.isSelected() && !jRdbIndustrial.isSelected() && !jRdbResidencial.isSelected()))
              resp = "Favor digitar os dados";
         else {
                  Consumidor c = new Consumidor();
                  c.setNumero(Integer.parseInt(jTxtNumero.getText()));
                  c.setNome(jTxtNome.getText());
                  Object o = cB.getBusca(Integer.parseInt(jLblCodigo.getText()), 0);
                  Bairro b = (Bairro) o;
                  c.setBairro(b);
                  //c.setBairro((String)jCmbBairro.getSelectedItem());
                  c.setTipo(jRdbComercial.isSelected() ? 'C' : jRdbIndustrial.isSelected() ? 'I' : 'R');
                  c.setQuantKwh((int)jSpnQuant.getValue());
                  c.setDataV(jFtdTxtDataV.getText());
                  c.setPrecoKwh(Float.parseFloat(Diversos.converterValor(jTxtPrecoKwh.getText())));
                  c.setAtraso(jChkSim.isSelected());
                   if (!cC.setManipular(c, opcao)) {
                       resp = "Problemas ao " + (opcao == 'A' ? "atualizar" : opcao == 'E' ?  
                   	   "remover" : " inserir") + " os dados do(a) consumidor(a) " + c.getNome();
                       jBtnAlterar.setEnabled(false);
                       jBtnExcluir.setEnabled(false);
                      jBtnIncluir.setEnabled(false);
                   }   
                   else {
            	           jLblPrecoF.setText("Preco a pagar de "+
                          Diversos.doisDigitos(1).format(c.precoReal())); 
                          resp = "O(A) consumidor(a) " +  c.getNome();
                         switch(opcao) {
                              case 'A' :  resp += "\nFoi atualizado(a) ";
                                               break;
                              case 'E' :  resp  +=  "\nFoi removido(a) ";
                                              limpar();
                                              jBtnAlterar.setEnabled(false);
                                              jBtnExcluir.setEnabled(false);
                                              break;
                              case 'I' :  resp +=  "\nFoi inserido(a) ";
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
		int numero;
        Consumidor c;
        if (!Diversos.testaNum(jTxtNumero.getText(), titulo))
            jTxtNumero.setText(""); // converter texto para numero
        else if (!Diversos.intervalo(Integer.parseInt(jTxtNumero.getText()), 0 ,0 , titulo))
                 jTxtNumero.setText(""); //testar se é maior que zero
             else {
                   numero = Integer.parseInt(jTxtNumero.getText());
                   Object o = cC.getBusca(numero,0);
                   if (o == null) {
                      jBtnIncluir.setEnabled(true);
                      Diversos.mostrarDados("Consumidor(a)  " + numero + " inexistente", titulo, true);
                   }    
                   else {
                        c = (Consumidor) o;
                        carregaObjetos(c); 
                        jBtnAlterar.setEnabled(true);
                        jBtnExcluir.setEnabled(true);
                  } 
                  jTxtNumero.setEditable(false);
            }
	}
	
	private void selecionaChave() {
		int ind = jCmbBairro.getSelectedIndex();
        if (ind > -1) {
           Object o = listaB.get(ind);
           Bairro b = (Bairro) o;
          jLblCodigo.setText(String.valueOf(b.getCodigo()));
        }
	}
}
