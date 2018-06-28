/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;

/**
 *
 * @author Esther Lara
 */
public class Consulta extends JFrame {

    public JLabel lblCarnet, lblNombre, lblApellido, lblEdad, lblUniversidad, lblEstado;
    public JTextField carnet, nombres, apellidos, edad;
    public JComboBox universidad;
    ButtonGroup estado = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    public JPanel table;
    public JButton buscar, eliminar, insertar, limpiar, actualizar;
    private static final int ANCHOC = 150, ALTOC = 25;

    DefaultTableModel tm;

    public Consulta() {

        super("Inscripcion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCarnet);
        container.add(lblNombre);
        container.add(lblApellido);
        container.add(lblEdad);
        container.add(lblUniversidad);
        container.add(lblEstado);
        container.add(carnet);
        container.add(nombres);
        container.add(apellidos);
        container.add(edad);
        container.add(universidad);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600, 600);
        eventos();
    }

    private void agregarLabels() {

        lblCarnet = new JLabel("Carnet");
        lblNombre = new JLabel("Nombre");
        lblApellido = new JLabel("Apellido");
        lblEdad = new JLabel("Edad");
        lblUniversidad = new JLabel("Universidad");
        lblEstado = new JLabel("Estado de Inscripcion");
        lblCarnet.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(20, 45, ANCHOC, ALTOC);
        lblApellido.setBounds(120, 45, ANCHOC, ALTOC);
        lblEdad.setBounds(10, 100, 75, ALTOC);
        lblUniversidad.setBounds(10, 140, ANCHOC, ALTOC);
        lblEstado.setBounds(10, 180, ANCHOC, ALTOC);

    }

    private void formulario() {
        carnet = new JTextField();
        nombres = new JTextField();
        apellidos = new JTextField();
        edad = new JTextField();
        universidad = new JComboBox();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        table = new JPanel();
        universidad.addItem("UCA");
        universidad.addItem("UDB");
        universidad.addItem("UFG");
        universidad.addItem("UGB");
        estado = new ButtonGroup();
        estado.add(si);
        estado.add(no);

        //-------------------------------------------
        carnet.setBounds(140, 10, ANCHOC, ALTOC);
        nombres.setBounds(20, 70, ANCHOC, ALTOC);
        apellidos.setBounds(200, 70, ANCHOC, ALTOC);
        universidad.setBounds(140, 140, ANCHOC, ALTOC);
        edad.setBounds(140, 100, 75, ALTOC);
        si.setBounds(140, 180, 50, ALTOC);
        no.setBounds(210, 182, 50, ALTOC);

        buscar.setBounds(300, 10, 75, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);

        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }

    private void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {

                    case 0:

                        return String.class;

                    case 1:

                        return String.class;

                    case 2:

                        return String.class;

                    case 3:

                        return String.class;

                    default:

                        return Boolean.class;

                }

            }

        };

        tm.addColumn("Carnet");
        tm.addColumn("Nombre");
        tm.addColumn("Apellido");
        tm.addColumn("Universidad");
        tm.addColumn("Edad");
        tm.addColumn("Estado de Inscripcion");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getCarnet(), fi.getNombres(), fi.getApellidos(), fi.getUniversidad(), fi.getEdad(), fi.getEstado()});

        }

        resultados.setModel(tm);

    }

    private void eventos() {

        insertar.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                FiltroDao fd = new FiltroDao();

                Filtro f = new Filtro(carnet.getText(), nombres.getText(), apellidos.getText(), universidad.getSelectedItem().toString(), Integer.parseInt( edad.getText()), true);

                if (no.isSelected()) {

                    f.setEstado(false);

                }

                if (fd.create(f)) {

                    JOptionPane.showMessageDialog(null, "Inscripcion registrada con éxito");

                    limpiarCampos();

                    llenarTabla();

                } else {

                    JOptionPane.showMessageDialog(null, "Ocurrio un problema con la creación de la Inscripcion.");

                }

            }

        });

        actualizar.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                FiltroDao fd = new FiltroDao();

                Filtro f = new Filtro(carnet.getText(), nombres.getText(), apellidos.getText(), universidad.getSelectedItem().toString(), Integer.parseInt( edad.getText()), true);

                if (no.isSelected()) {

                    f.setEstado(false);

                }

                if (fd.update(f)) {

                    JOptionPane.showMessageDialog(null, "Filtro modificado con éxito");

                    limpiarCampos();

                    llenarTabla();

                } else {

                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de creación de este filtro.");

                }

            }

        });

        eliminar.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                FiltroDao fd = new FiltroDao();

                Filtro f = new Filtro(carnet.getText(), nombres.getText(), apellidos.getText(), universidad.getSelectedItem().toString(), Integer.parseInt( edad.getText()), true);

                if (fd.delete(carnet.getText())) {

                    JOptionPane.showMessageDialog(null, "Inscripcion eliminada con éxito");

                    limpiarCampos();

                    llenarTabla();

                } else {

                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar la Inscripcion.");

                }

            }

        });

        buscar.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                FiltroDao fd = new FiltroDao();

                Filtro f = fd.read(carnet.getText());

                if (f == null) {

                    JOptionPane.showMessageDialog(null, "El Filtro buscado no ha sido encontrado");

                } else {

                    carnet.setText(f.getCarnet());

                    nombres.setText(f.getNombres());

                    apellidos.setText(f.getApellidos());
                    universidad.setSelectedItem(f.getUniversidad());

                    if (f.getEstado()) {

                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public void limpiarCampos() {
        carnet.setText("");
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        universidad.setSelectedItem("UCA");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
}
