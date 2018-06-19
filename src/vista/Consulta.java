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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame {
    public JLabel lblCarnet, lblUniversidad, lblNombre, lblApellidos,lblEdad, lblExistencia, lblEstado;

    public JTextField carnet, nombre, apellido, edad, estado;
    public JComboBox universidad;

    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;
    
    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Alumnos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCarnet);
        container.add(lblUniversidad);
        container.add(lblNombre);
        container.add(lblApellidos);
        container.add(lblEdad);
        container.add(lblExistencia);
        container.add(lblEstado);
        container.add(carnet);
        container.add(universidad);
        container.add(nombre);
        container.add(apellido);
        container.add(edad);
        container.add(estado);
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
        lblUniversidad = new JLabel("Universidad");
        lblEstado = new JLabel("Estado");
        lblApellidos = new JLabel("Apellidos");
        lblNombre = new JLabel("Nombre");
        lblEdad = new JLabel("Edad");
        lblExistencia = new JLabel("Estado");
        lblCarnet.setBounds(10, 10, ANCHOC, ALTOC);
        lblUniversidad.setBounds(10, 60, ANCHOC, ALTOC);
        lblApellidos.setBounds(10, 100, ANCHOC, ALTOC);
        lblNombre.setBounds(90, 100, ANCHOC, ALTOC);
        lblEdad.setBounds(10, 140, ANCHOC, ALTOC);
        lblExistencia.setBounds(10, 180, ANCHOC, ALTOC);
    }

    private void formulario() {
        carnet = new JTextField();
        universidad = new JComboBox();
        nombre = new JTextField();
        apellido = new JTextField();
        edad = new JTextField();
        estado = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();

        universidad.addItem("uca");
        universidad.addItem("matias");
        universidad.addItem("don bosco");
        universidad.addItem("evangelica");

        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        //-------------------------------------------
        carnet.setBounds(140, 10, ANCHOC, ALTOC);
        apellido.setBounds(140, 60, ANCHOC, ALTOC);
        universidad.setBounds(140, 100, ANCHOC, ALTOC);
        estado.setBounds(140, 160, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
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
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Carnet");
        tm.addColumn("Nomnbre");
        tm.addColumn("Apellido");
        tm.addColumn("Universidad");
        tm.addColumn("Estado");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getCarnet(), fi.getNombre(), fi.getApellidos(), fi.getExistencia(), fi.getEdad()});
        }

        resultados.setModel(tm);

    }

    private void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(carnet.getText(),nombre.getText(), universidad.getSelectedItem().toString(),Integer.parseInt(edad.getText()),apellido.getText(), true);

                if (no.isSelected()) {
                    f.setExistencia(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "nombre registrado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema con la creación de este nombre.");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(carnet.getText(),nombre.getText(), universidad.getSelectedItem().toString(),Integer.parseInt(edad.getText()),apellido.getText(), true);

                if (no.isSelected()) {
                    f.setExistencia(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "nombre modificado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de creación de este nombre.");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(carnet.getText(),nombre.getText(), universidad.getSelectedItem().toString(),Integer.parseInt(edad.getText()),apellido.getText(), true);
                if (fd.delete(carnet.getText())) {
                    JOptionPane.showMessageDialog(null, "nombre eliminado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar este nombre.");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(carnet.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El nombre buscado no ha sido encontrado");
                } else {

                    carnet.setText(f.getCarnet());
                    universidad.setSelectedItem(f.getUniversidad());
                    edad.setText(Integer.toString(f.getEdad()));

                    if (f.getExistencia()) {
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
        universidad.setSelectedItem("uca");
        nombre.setText("");
        apellido.setText("");

    }

}
