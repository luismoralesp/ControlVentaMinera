/*
 * To change this license header,
 @Override
 public void onCaptura(BufferedImage image) {
 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
 }
 choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.google.gson.Gson;
import control.CCliente;
import control.CMaterial;
import control.CPedido;
import control.CSerial;
import control.CVehiculo;
import hardware.HSerial;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Material;
import modelo.Pedido;
import modelo.Vehiculo;

/**
 *
 * @author migue
 */
public class Panel extends javax.swing.JFrame {

    Image foto = null;
    private final DefaultTableModel dtm;
    DefaultComboBoxModel<Vehiculo> modelVehiculo = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<>();
    Webcam webcam1;
    final CSerial serial = CSerial.getInstacia();

    public Panel() {

        hilo_peso();

        try {
            webcam1 = Webcam.getDefault();

            System.out.println(webcam1);
        } catch (WebcamException e) {
            JOptionPane.showMessageDialog(this, "No se encontro camara", "Error", JOptionPane.ERROR_MESSAGE);
        }
        initComponents();
        this.dtm = new DefaultTableModel(new String[]{"Código", "Veículo", "Carga", "Cliente", "Producto"}, 0);
        tablaPedidos.setModel(dtm);
        clientes.setModel(modelCliente);
        vehiculos.setModel(modelVehiculo);
        //((Capturador)camara1).setLabel(jLabel4);
        setLocationRelativeTo(this);
        serial.initialize();
        configurar();

        this.setIconImage(new ImageIcon(getClass().getResource("/images/if_truck-front-03_2140055.png")).getImage());
        frameBascula.setFrameIcon(new ImageIcon(getClass().getResource("/images/if_truck-front-03_2140055.png")));
        frameCamaras.setFrameIcon(new ImageIcon(getClass().getResource("/images/if_truck-front-03_2140055.png")));
        frameConsola.setFrameIcon(new ImageIcon(getClass().getResource("/images/if_truck-front-03_2140055.png")));
        framePedidos.setFrameIcon(new ImageIcon(getClass().getResource("/images/if_truck-front-03_2140055.png")));
    }

    private void hilo_peso() {

        Timer timer = new Timer();
        TimerTask peso;
        final Gson gson = new Gson();
        peso = new TimerTask() {

            @Override
            public void run() {

                String peso = leer();
                if (!peso.equals("")) {

                    Map<String, String> map = gson.fromJson("{\"state\":\"waiting\"}", Map.class);
                    System.out.println(map.get("state"));
                    if (map.get("state").equals("waiting")) {
                        espera.setBackground(new Color(60, 148, 123));
                        cargando.setBackground(null);
                        cargado.setBackground(null);
                    } else if (map.get("state").equals("loading")) {
                        espera.setBackground(null);
                        cargando.setBackground(new Color(60, 148, 123));
                        cargado.setBackground(null);
                    } else if (map.get("state").equals("loaded")) {
                        espera.setBackground(null);
                        cargando.setBackground(null);
                        cargado.setBackground(new Color(60, 148, 123));
                    }
                    peso1.setText(map.get("load"));
                    peso2.setText(map.get("loading"));
                    peso3.setText(map.get("loaded"));
                }
                //System.out.println(peso);
            }
        };
        timer.scheduleAtFixedRate(peso, 3000, 160);
    }

    private void configurar() {
        modelVehiculo.removeAllElements();
        CVehiculo cv = CVehiculo.getInstacia();
        for (Vehiculo vehiculo : cv.getVehiculos()) {
            modelVehiculo.addElement(vehiculo);
        }
        modelCliente.removeAllElements();
        CCliente cc = CCliente.getInstacia();
        for (Cliente cliente : cc.getClientes()) {
            modelCliente.addElement(cliente);
        }
        CPedido cp = CPedido.getInstacia();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        for (Pedido pedido : cp.getPedidos()) {
            dtm.addRow(new Object[]{pedido, pedido.getVehiculo(), pedido.getTon(), pedido.getCliente(), pedido.getMaterial()});
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem4 = new javax.swing.JCheckBoxMenuItem();
        frameCamaras = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        camara1 = new Capturador(webcam1){
            public void onCaptura(BufferedImage image){
                eventoCaptura(image);
            }
        };
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        framePedidos = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        eliminarPedido = new javax.swing.JButton();
        jToolBar3 = new javax.swing.JToolBar();
        jLabel5 = new javax.swing.JLabel();
        clientes = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        vehiculos = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        fton = new javax.swing.JTextField();
        agregarPedido = new javax.swing.JButton();
        jToolBar4 = new javax.swing.JToolBar();
        frameConsola = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        consola = new javax.swing.JTextArea();
        frameBascula = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        peso1 = new javax.swing.JLabel();
        peso2 = new javax.swing.JLabel();
        peso3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        espera = new javax.swing.JLabel();
        cargado = new javax.swing.JLabel();
        cargando = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        jCheckBoxMenuItem4.setSelected(true);
        jCheckBoxMenuItem4.setText("jCheckBoxMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Control de venta Minera");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(1024, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        frameCamaras.setTitle("Cámaras");
        frameCamaras.setVisible(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        camara1.setBackground(new java.awt.Color(0, 0, 0));
        camara1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout camara1Layout = new javax.swing.GroupLayout(camara1);
        camara1.setLayout(camara1Layout);
        camara1Layout.setHorizontalGroup(
            camara1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        camara1Layout.setVerticalGroup(
            camara1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cámara 1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(camara1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(camara1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(351, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameCamarasLayout = new javax.swing.GroupLayout(frameCamaras.getContentPane());
        frameCamaras.getContentPane().setLayout(frameCamarasLayout);
        frameCamarasLayout.setHorizontalGroup(
            frameCamarasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameCamarasLayout.setVerticalGroup(
            frameCamarasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        framePedidos.setTitle("Pedidos");
        framePedidos.setName(""); // NOI18N
        framePedidos.setVisible(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tablaPedidos);

        jToolBar2.setRollover(true);

        eliminarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_Close_1891023.png"))); // NOI18N
        eliminarPedido.setText("Eliminar");
        eliminarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPedidoActionPerformed(evt);
            }
        });
        jToolBar2.add(eliminarPedido);

        jToolBar3.setRollover(true);

        jLabel5.setText("Cliente: ");
        jToolBar3.add(jLabel5);

        clientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jToolBar3.add(clientes);

        jLabel1.setText("Vehículo:");
        jToolBar3.add(jLabel1);

        vehiculos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        vehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehiculosActionPerformed(evt);
            }
        });
        jToolBar3.add(vehiculos);

        jLabel2.setText("Ton: ");
        jToolBar3.add(jLabel2);

        fton.setText("5.0");
        jToolBar3.add(fton);

        agregarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_Plus_green_1891032 (1).png"))); // NOI18N
        agregarPedido.setText("Agregar");
        agregarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPedidoActionPerformed(evt);
            }
        });
        jToolBar3.add(agregarPedido);

        jToolBar4.setRollover(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout framePedidosLayout = new javax.swing.GroupLayout(framePedidos.getContentPane());
        framePedidos.getContentPane().setLayout(framePedidosLayout);
        framePedidosLayout.setHorizontalGroup(
            framePedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        framePedidosLayout.setVerticalGroup(
            framePedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        frameConsola.setTitle("Consola");
        frameConsola.setVisible(true);

        consola.setEditable(false);
        consola.setColumns(20);
        consola.setRows(5);
        jScrollPane1.setViewportView(consola);

        javax.swing.GroupLayout frameConsolaLayout = new javax.swing.GroupLayout(frameConsola.getContentPane());
        frameConsola.getContentPane().setLayout(frameConsolaLayout);
        frameConsolaLayout.setHorizontalGroup(
            frameConsolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        frameConsolaLayout.setVerticalGroup(
            frameConsolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        frameBascula.setTitle("Báscula");
        frameBascula.setVisible(true);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Báscula"));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Peso"));
        jPanel4.setPreferredSize(new java.awt.Dimension(279, 236));

        peso1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        peso1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        peso1.setText("...");
        peso1.setBorder(javax.swing.BorderFactory.createTitledBorder("Peso actual"));
        peso1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        peso2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        peso2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        peso2.setText("...");
        peso2.setBorder(javax.swing.BorderFactory.createTitledBorder("Peso a cargar"));
        peso2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        peso3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        peso3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        peso3.setText("...");
        peso3.setBorder(javax.swing.BorderFactory.createTitledBorder("Última carga"));
        peso3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(peso3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(peso2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(peso1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(peso1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(peso2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(peso3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Simulación"));

        espera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_truck-front-03_2140055.png"))); // NOI18N

        cargado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_truck-back-03_2140057_1.png"))); // NOI18N

        cargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_truck-front-03_2140055_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(espera, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addComponent(cargando, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(cargado, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cargado, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(espera, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cargando, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameBasculaLayout = new javax.swing.GroupLayout(frameBascula.getContentPane());
        frameBascula.getContentPane().setLayout(frameBasculaLayout);
        frameBasculaLayout.setHorizontalGroup(
            frameBasculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameBasculaLayout.setVerticalGroup(
            frameBasculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jMenu2.setText("Programa");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_Close_1891023.png"))); // NOI18N
        jMenuItem1.setText("Salir");
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Registro");

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_emblem-people_24702.png"))); // NOI18N
        jMenu3.setText("Clientes");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_Plus_green_1891032 (1).png"))); // NOI18N
        jMenuItem2.setText("Agregar cliente");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_2040_searchpad_change_c12_2400494.png"))); // NOI18N
        jMenuItem3.setText("Buscar cliente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenu1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_truck-front-03_2140055 (2).png"))); // NOI18N
        jMenu4.setText("Vehículos");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_Plus_green_1891032 (1).png"))); // NOI18N
        jMenuItem4.setText("Agregar vehículo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_2040_searchpad_change_c12_2400494.png"))); // NOI18N
        jMenuItem5.setText("Buscar vehículo");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenu1.add(jMenu4);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_trailer-front-03_2140056.png"))); // NOI18N
        jMenu9.setText("Materiales");

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_Plus_green_1891032 (1).png"))); // NOI18N
        jMenuItem7.setText("Agregar material");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem7);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/if_2040_searchpad_change_c12_2400494.png"))); // NOI18N
        jMenuItem8.setText("Buscar material");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem8);

        jMenu1.add(jMenu9);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(frameConsola)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(frameCamaras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(frameBascula)
                            .addComponent(framePedidos))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(framePedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frameBascula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(frameCamaras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(frameConsola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPedidoActionPerformed
        agregarPedido();
    }//GEN-LAST:event_agregarPedidoActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        agregarVehiculo();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        agregarCliente();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void eliminarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarPedidoActionPerformed
        eliminarPedido();
    }//GEN-LAST:event_eliminarPedidoActionPerformed

    private void vehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehiculosActionPerformed
        Vehiculo vehiculo = (Vehiculo) modelVehiculo.getSelectedItem();
        if (vehiculo != null) {
            fton.setText(vehiculo.getCapacidad() + "");
        }
    }//GEN-LAST:event_vehiculosActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        agregarMaterial();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        buscarCliente();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        buscarVehiculo();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        buscarMaterial();
    }//GEN-LAST:event_jMenuItem8ActionPerformed
    void enviar(final int comm) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    serial.send(comm);
                } catch (InterruptedException ie) {
                } catch (IOException ex) {
                    Logger.getLogger(HSerial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
    }

    String leer() {
        return serial.read();
    }

    void init_mover() {
        enviar(3);
    }

    void stop_mover() {
        enviar(4);
    }

    private void buscarCliente() {
        new Buscar<Cliente>(new String[]{"Identificacion", "Nombre"}, CCliente.getInstacia().getClientes()) {

            @Override
            public String[] likeRow(Cliente row) {
                return new String[]{row.getIdent(), row.getNombre()};
            }

            @Override
            public void update(Cliente row, String[] data) {
                row.setIdent(data[0]);
                row.setNombre(data[1]);

            }
        }.setVisible(true);
    }

    private void buscarVehiculo() {
        new Buscar<Vehiculo>(new String[]{"Placa", "Capacidad"}, CVehiculo.getInstacia().getVehiculos()) {

            @Override
            public String[] likeRow(Vehiculo row) {
                return new String[]{row.getPlaca(), row.getCapacidad() + ""};
            }

            @Override
            public void update(Vehiculo row, String[] data) {
                row.setPlaca(data[0]);
                row.setCapacidad(Float.parseFloat(data[1]));

            }
        }.setVisible(true);
    }

    private void buscarMaterial() {
        new Buscar<Material>(new String[]{"Nombre"}, CMaterial.getInstacia().getMateriales()) {

            @Override
            public String[] likeRow(Material row) {
                return new String[]{row.getNombre()};
            }

            @Override
            public void update(Material row, String[] data) {
                row.setNombre(data[0]);

            }
        }.setVisible(true);
    }

    private void eventoCaptura(BufferedImage image) {
        Interprete interprete = Interprete.getInstancia();
        try {
            String lectura = interprete.leer(image);
            consola.setText(lectura);
            Pedido pedido = buscarPedido(lectura);
            if (pedido != null) {
                JOptionPane.showMessageDialog(rootPane, pedido);
                enviar((int) pedido.getTon());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al intentar leer la imagen.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Pedido buscarPedido(String lectura) {
        for (int i = 0; i < dtm.getRowCount(); i++) {
            Pedido pedido = (Pedido) dtm.getValueAt(i, 0);
            if (lectura.contains(pedido.getVehiculo().getPlaca()) || lectura.replace(" ", "").contains(pedido.getVehiculo().getPlaca())) {
                dtm.removeRow(i);
                CPedido.getInstacia().delPedido(pedido);
                return pedido;
            }
        }
        return null;
    }

    private void agregarPedido() {
        Cliente cliente = (Cliente) modelCliente.getSelectedItem();
        Vehiculo vehiculo = (Vehiculo) modelVehiculo.getSelectedItem();
        String ton = fton.getText();
        try {
            float tonn = Float.parseFloat(ton);
            if (tonn <= vehiculo.getCapacidad()) {
                Pedido pedido = new Pedido(cliente, vehiculo, tonn);
                EscojerMaterial escojerMaterial = new EscojerMaterial(this, true);
                escojerMaterial.setVisible(true);
                if (escojerMaterial.getReturnStatus() == EscojerMaterial.RET_OK) {
                    pedido.setMaterial(escojerMaterial.getMaterial());
                    CPedido cp = CPedido.getInstacia();
                    cp.addPedido(pedido);
                    configurar();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Las toneladas(Ton) no deben superar la capacidad del vehículo.", "Advertencia!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "El campo 'Ton' debe ser un número.", "Advertencia!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void agregarVehiculo() {
        AgrearVehiculo agrearVehiculo = new AgrearVehiculo(this, true);
        agrearVehiculo.setVisible(true);
        configurar();
    }

    private void agregarCliente() {
        AgrearCliente agrearCliente = new AgrearCliente(this, true);
        agrearCliente.setVisible(true);
        configurar();
    }

    private void agregarMaterial() {
        AgrearMaterial agrearMaterial = new AgrearMaterial(this, true);
        agrearMaterial.setVisible(true);
        configurar();
    }

    private void eliminarPedido() {
        int selected = tablaPedidos.getSelectedRow();
        Pedido pedido = (Pedido) dtm.getValueAt(selected, 0);
        if (pedido != null) {
            int showConfirmDialog = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el pedido?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                CPedido cp = CPedido.getInstacia();
                cp.delPedido(pedido);
                configurar();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        serial.close();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarPedido;
    private javax.swing.JPanel camara1;
    private javax.swing.JLabel cargado;
    private javax.swing.JLabel cargando;
    private javax.swing.JComboBox clientes;
    private javax.swing.JTextArea consola;
    private javax.swing.JButton eliminarPedido;
    private javax.swing.JLabel espera;
    private javax.swing.JInternalFrame frameBascula;
    private javax.swing.JInternalFrame frameCamaras;
    private javax.swing.JInternalFrame frameConsola;
    private javax.swing.JInternalFrame framePedidos;
    private javax.swing.JTextField fton;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JLabel peso1;
    private javax.swing.JLabel peso2;
    private javax.swing.JLabel peso3;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JComboBox vehiculos;
    // End of variables declaration//GEN-END:variables

}
