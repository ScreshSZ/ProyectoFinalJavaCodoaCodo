
package com.mycompany.java_crud_mysql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class CAlumnos {
 
    int codigo;
    String nombreAlumno;
    String apellidoAlumno;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }
    
    public void insertarAlumno(JTextField paramNombres, JTextField paramApellidos){
        
        setNombreAlumno(paramNombres.getText());
        setApellidoAlumno(paramApellidos.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "insert into Alumnos (nombre,apellido) values (?,?);";
        
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto el alumno");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error no se inserto " + e.toString());
        }
        
    }
    
    public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
        CConexion objetoContexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTable = new TableRowSorter<>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTable);
        
        String sql = "Select * FROM Alumos";
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        
        paramTablaTotalAlumnos.setModel(modelo);
        
        sql = "select * from Alumnos;";
        
        String[] datos = new String[3];
        Statement st;
        
        try{
            st = objetoContexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                modelo.addRow(datos);
            }
            paramTablaTotalAlumnos.setModel(modelo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
        }
        
    }
    
    public void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos){
        try{
            int fila = paramTablaAlumnos.getSelectedRow();
            if(fila >= 0){
                paramId.setText((String) (paramTablaAlumnos.getValueAt(fila, 0)));
                paramNombres.setText((String) (paramTablaAlumnos.getValueAt(fila, 1)));
                paramApellidos.setText((String) (paramTablaAlumnos.getValueAt(fila, 2)));
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de seleccion " + e.toString());
        }
    }
    
    public void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos ){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumno(paramNombres.getText());
        setApellidoAlumno(paramApellidos.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "UPDATE Alumnos SET alumnos.nombre = ?, alumnos.apellido =? WHERE alumnos.id = ?;";
        
        try{
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            cs.setInt(3, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion Exitosa");
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, "No se modifico " + e.toString());
            
            
            
        }
        
        
        
    }
    
    public void EliminarAlumnos(JTextField paramCodigo){
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM Alumnos WHERE alumnos.id = ?";
        
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getCodigo());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar " + e.toString() );
        }
        
        
    }
    
    
    
}
