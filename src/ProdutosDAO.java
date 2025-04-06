/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        try {
            conn = new conectaDAO().connectDB();
            
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (? , ? , ?)";
            prep = conn.prepareStatement(sql);
            
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.executeUpdate();
            
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        try {
            String sql = "SELECT * FROM produtos";
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
             
            
            while (resultset.next()){
               
                ProdutosDTO prod = new ProdutosDTO();
                
                prod.setId(resultset.getInt("id"));
                prod.setNome(resultset.getString("nome"));
                prod.setValor(resultset.getInt("valor"));
                prod.setStatus(resultset.getString("status"));
                
                listagem.add(prod);
                  
            }
            prep.close();
                resultset.close();
                conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listagem;
    }
    
    public void venderProduto(int id){
                     
        try {
            String sql = "UPDATE produtos SET status = ? WHERE ID = ?";
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            
            prep.setString(1, "Vendido");
            prep.setInt(2, id);
            
            prep.executeUpdate();
            prep.close();
            conn.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao vender o produto!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        
        try {
            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
             
            
            while (resultset.next()){
               
                ProdutosDTO prod = new ProdutosDTO();
                
                prod.setId(resultset.getInt("id"));
                prod.setNome(resultset.getString("nome"));
                prod.setValor(resultset.getInt("valor"));
                prod.setStatus(resultset.getString("status"));
                
                listagem.add(prod);
                  
            }
            prep.close();
                resultset.close();
                conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listagem;
    }
}

