/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.service;

import com.produccion.dao.DAOException;
import com.produccion.datatransfers.MaterialDTO;
import com.produccion.entities.Material;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface MaterialesService {
    public List<MaterialDTO> getMateriales() throws DAOException;
    public void deleteMaterial(Integer idMaterial) throws DAOException;
    public Integer addMaterial(MaterialDTO material) throws DAOException;
    public void updateMaterial(Material material) throws DAOException;
    public MaterialDTO getMaterialById(Integer idMaterial) throws DAOException;
    public List<MaterialDTO> getMaterialesPorReceta(Integer idReceta) throws DAOException;
}
