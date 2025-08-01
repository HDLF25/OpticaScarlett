package GUI;

public class Changelog extends javax.swing.JPanel {

    public Changelog() {
        initComponents();
        jTextArea1.setEditable(false);
        jTextArea1.setCaretPosition(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        lblLeng = new javax.swing.JLabel();
        lblLengJava = new javax.swing.JLabel();
        lblFirma = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("v. 1.14 Rev. 241217\n\n• Se crea opción de Consultar OT en Ficha de Cliente.\n• Correcciones menores relacionados a la conexión\na la base de datos.\n\nv. 1.13 Rev. 241201\n\n• Se agrega Tipo Cristal en reporte de Ficha de Cliente.\n• Se añade botón de Reabrir OT en Ficha Cliente.\n• Se corrige error que permitía cerrar OT aunque el\nsaldo no sea 0.\n\nv. 1.12 Rev. 241121\n\n• Módulo de usuarios funcional (Es posible crear,\nmodificar, eliminar, activar, inactivar y cambiar\ncontraseña de los usuarios para el sistema).\n• Se agregan la función de agregar y cambiar\nimágenes a los artíiculos ya existentes o en artículos\nnuevos.\n• Se agrega observación y usuario al resumen de\nventas.\n\nv. 1.11 Rev. 241120\n\n• Se habilitó los permisos dinámicos por usuario.\n• Cambios menores en Resumen de Ventas.\n• Correción en los filtros de busqueda.\n\nv. 1.10 Rev. 241119\n\n• Se implementa función de permisos de usuarios.\n\nv. 1.09 Rev. 241112\n\n• Se añade nueva opción de Usuarios para disponer\nla creación, edición, habilitación/deshabilitación y\neliminación de usuarios para el sistema.\n\nv. 1.08 Rev. 241105\n\n• Cambios realizados en el reporte de ListadoFicha\npor cambios hechos en la base de datos.\n\nv. 1.07 Rev. 241101\n\n• Implementación completada de Resumen de\nVentas.\n\nv. 1.06 Rev. 241028\n\n• Implementación completada de Métodos de Pago.\n\nv. 1.05 Rev. 241021\n\n• Se implementa funcionalidad al campo Métodos\nde Pago en Ficha Cliente.\n• Reorganización de los campos dentro de Ficha\nCliente.\n• Correcciones menores relacionados a los filtros\nde búsqueda.\n\nv. 1.04 Rev. 241007\n\n• Se añadió Métodos de Pago al Menú Principal.\n• Se agregó solicitud de usuario para las opciones de\nModificación y Eliminación.\n• Correcciones menores.\n\nv. 1.03 Rev. 240917\n\nCarácteristicas implementadas:\n• Se añadió Resumen de Ventas en el Menú Principal.\n• Se añadió Método de Pago en Ficha Cliente.\n• Cambios menores.\n\nv. 1.02 Rev. 221206\n\nCarácteristicas añadidas:\n• Correcciones en los filtros de búsqueda ya\nexistentes.\n• Habilitación del botón \"Buscar Ficha/OT\"\n• Añadido de generador de reportes con buscador\npor ID o CI del cliente, búsqueda entre fechas y\nselección de datos por estado.\n• Botón de \"Cerrar OT\" añadido en Ficha del Cliente,\na fin de darcierre a OTs abiertas y/o con montos sin\nfiniquitar.\n• Inclusión de Changelog (Registro de cambios) a\nfin de mantener historial de cambios realizados al\nsistema.\n\n- Se incluyendo iconos a las ventanas para mejorar\nla apariencia del sistema.\n\nv. 1.0 Rev. 220809\n\n• Inicio del sistema\n• Añadido de referenciales y registro de clientes,\nfichas del cliente, artículos, marcas, ciudades y\nentrada/salida de stock.");
        jTextArea1.setCaretPosition(0);
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTextArea1);

        lblLeng.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLeng.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLeng.setText("IDE:");

        lblLengJava.setText("Netbeans IDE 22 (Java JDK 8)");

        lblFirma.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFirma.setText("Software hecho con ♥ por @hdavidlopez © 2024");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Database:");

        jLabel2.setText("PostgreSQL 12");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Versión:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Revisión:");

        jLabel5.setText("1.14");

        jLabel6.setText("241217");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblFirma))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLeng, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLengJava)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLeng)
                    .addComponent(lblLengJava))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFirma)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblFirma;
    private javax.swing.JLabel lblLeng;
    private javax.swing.JLabel lblLengJava;
    // End of variables declaration//GEN-END:variables
}
