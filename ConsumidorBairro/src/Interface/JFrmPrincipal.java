package Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.plaf.FontUIResource;

import Controle.ControleGeral;
import Util.Diversos;

import javax.swing.JToolBar;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class JFrmPrincipal extends JFrame {

	private JPanel jContentPane;
    private JLabel jLblAutor;
    private JLabel jLblData;
    private JLabel jLblHora; 
    private JSeparator jSepAutor;
    private JSeparator jSepHora;
    private JSeparator jSepSair;
    private JMenuBar jMnBPrincipal;
    private JMenuItem jMnISair;
    private final String titulo;

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	  //	EventQueue.invokeLater(new Runnable() {
	  //		public void run() {
				try {
					JFrmPrincipal frame = new JFrmPrincipal();
					frame.setVisible(true);
					frame.relogio();
				} catch (Exception e) {
					e.printStackTrace();
				}
	//      }
    //	});
	}

	/**
	 * Create the frame.
	 */
	public JFrmPrincipal() {
		//setAlwaysOnTop(true);
		//setUndecorated(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				jLblAutor.setText("  Autor : Grupo de desenvolvimento    ");
		        jLblData.setText(" Data : " +
		        DateFormat.getDateInstance().format(new Date())+ "                        ");
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
			
			}
		});
		titulo = "Cadastrar alocação de funcionários nos projetos";
		setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		setTitle("                                                                                      " + titulo);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 968, 625);
		jContentPane = new JPanel();
		jContentPane.setBackground(Color.CYAN);
		jContentPane.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JToolBar jTolBInfo = new JToolBar();
		jTolBInfo.setBounds(0, 575, 961, 24);
		jTolBInfo.setBackground(Color.CYAN);
		jTolBInfo.setRollover(true);
		jTolBInfo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jTolBInfo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jTolBInfo);
		
		jLblAutor = new JLabel("");
		jTolBInfo.add(jLblAutor);
		jLblAutor.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		
		jSepAutor = new JSeparator();
		jSepAutor.setBorder(null);
		jSepAutor.setOrientation(SwingConstants.VERTICAL);
		jSepAutor.setForeground(Color.BLACK);
		jTolBInfo.add(jSepAutor);
		
		jLblData = new JLabel("");
		jLblData.setVerticalAlignment(SwingConstants.TOP);
		jLblData.setHorizontalTextPosition(SwingConstants.LEFT);
		jLblData.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTolBInfo.add(jLblData);
		
		jSepHora = new JSeparator();
		jSepHora.setOrientation(SwingConstants.VERTICAL);
		jSepHora.setForeground(Color.BLACK);
		jTolBInfo.add(jSepHora);
		
		jLblHora = new JLabel("");
		jLblHora.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTolBInfo.add(jLblHora);
		
		jMnBPrincipal = new JMenuBar();
		jMnBPrincipal.setBounds(0, 0, 958, 24);
		jMnBPrincipal.setBackground(Color.WHITE);
		jMnBPrincipal.setForeground(Color.BLACK);
		jMnBPrincipal.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 11));
		jContentPane.add(jMnBPrincipal);
		
		JMenu jMnArquivo = new JMenu("Arquivo");
		jMnArquivo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnArquivo.setHorizontalAlignment(SwingConstants.CENTER);
		jMnBPrincipal.add(jMnArquivo);
		
		jMnISair = new JMenuItem("Sair");
		jMnISair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Diversos.confirmar("Deseja finalizar", titulo))
					System.exit(0);
					
			}
		});
		jMnISair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		
		jSepSair = new JSeparator();
		jSepSair.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnArquivo.add(jSepSair);
		jMnISair.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnISair.setHorizontalAlignment(SwingConstants.CENTER);
		jMnArquivo.add(jMnISair);
		
		JMenu jMnCadastrar = new JMenu("Cadastrar");
		jMnCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		jMnCadastrar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnBPrincipal.add(jMnCadastrar);
		
		JSeparator jSepCargo = new JSeparator();
		jSepCargo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(jSepCargo);
		
		JMenuItem jMnIBairro = new JMenuItem("Bairro");
		jMnIBairro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrmBairro frame = new JFrmBairro();
				frame.setVisible(true);
			}
		});
		jMnIBairro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		jMnIBairro.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnIBairro.setHorizontalAlignment(SwingConstants.CENTER);
		jMnCadastrar.add(jMnIBairro);
		
		JSeparator jSepFuncionario = new JSeparator();
		jSepFuncionario.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(jSepFuncionario);
		
		JMenuItem jMnIConsumidor = new JMenuItem("Consumidores");
		jMnIConsumidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrmConsumidor frame = new JFrmConsumidor();
				frame.setVisible(true);
			}
		});
		jMnIConsumidor.setHorizontalAlignment(SwingConstants.CENTER);
		jMnIConsumidor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		jMnIConsumidor.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnCadastrar.add(jMnIConsumidor);
		
		JSeparator jSEpProjeto = new JSeparator();
		jSEpProjeto.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(jSEpProjeto);
		
		JSeparator jSepAP = new JSeparator();
		jSepAP.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(jSepAP);
		
		JSeparator JsepAloca = new JSeparator();
		JsepAloca.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(JsepAloca);
		
		JMenuItem jMnIManutencao = new JMenuItem("Manuten\u00E7\u00E3o");
		jMnIManutencao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manutencao();
			}
		});
		jMnIManutencao.setHorizontalAlignment(SwingConstants.CENTER);
		jMnIManutencao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		jMnIManutencao.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnCadastrar.add(jMnIManutencao);
		setResizable(false);		
		setLocationRelativeTo(null);
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 13)));
            UIManager.put("OptionPane.messageForeground", Color.BLUE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
          } 

	}
	
	public void manutencao() {
	   ControleGeral cM = new ControleGeral(0);
	   cM.manutencao();	
	}
	
	public void relogio() {
		  try {
	    	  Thread.sleep(1000);
	    	  while(true)  
	    		   jLblHora.setText
	              ("Hora : " + DateFormat.getTimeInstance().format(new Date().getTime()) + "                  ");		 
	     } 
	     catch (InterruptedException e){
	         Diversos.mostrarDados("Problema ao acionar a hora " + e.getMessage(), titulo, false);  
	       }
	}	
}
