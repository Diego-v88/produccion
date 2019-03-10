/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.service;

import com.produccion.dao.DAOException;
import com.produccion.dao.MaterialesDAO;
import com.produccion.datatransfers.MaterialDTO;
import com.produccion.entities.Material;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.MapperUtils;

/**
 *
 * @author Diego
 */
@Service("materialesservice")
@Transactional
public class MaterialesServiceImpl implements MaterialesService{
    @Autowired
    private MaterialesDAO materialesdao;
    
    private ModelMapper modelMapper = new ModelMapper();
    
    @Override
    public List<MaterialDTO> getMateriales() throws DAOException{
        List<MaterialDTO> materiales = null;
        try {
            materiales = MapperUtils.mapAll(materialesdao.findAll(Material.class), MaterialDTO.class);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo listar los materiales", e);
        }
        return materiales;
    }
    
    @Override
    public List<MaterialDTO> getMaterialesPorReceta(Integer idReceta) throws DAOException{
        List<MaterialDTO> materiales = null;
        try {
            materiales = MapperUtils.mapAll(materialesdao.getMaterialesPorReceta(idReceta), MaterialDTO.class);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo listar las recetas", e);
        }
        return materiales;
    }
    
    @Override
    public void deleteMaterial(Integer idMaterial) throws DAOException{
        try {
            materialesdao.deleteById(Material.class,idMaterial);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo eliminar el material", e);
        }
    }
    
    @Override
    public Integer addMaterial(MaterialDTO materialdto) throws DAOException{
        Integer id = -1;
        try {
            Material material = modelMapper.map(materialdto, Material.class);
            material.setFechaalta(new Date());
            id = materialesdao.save(material);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo agregar el material", e);
        }
        return id;
    }
    
    @Override
    public void updateMaterial(Material material) throws DAOException {
        try {
            materialesdao.update(material);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo actualizar el material", e);
        }
    }
    
    @Override
    public MaterialDTO getMaterialById(Integer idMaterial) throws DAOException {
        MaterialDTO material = null;
        try {
            material = modelMapper.map(materialesdao.findByID(Material.class,idMaterial), MaterialDTO.class);
        } catch (Exception e) {
            throw new DAOException("Hubo un inconveniente y no se pudo traer el material", e);
        }
        return material;
    }
}
