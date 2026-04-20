/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  jennifer barrios
 * Created: 17/04/2026
 */

CREATE DATABASE IF NOT EXISTS sig;
USE sig;

INSERT INTO `Vendedores` (`codigo_vendedor`,`nombre_vendedor`,`direccion_vendedor`,`telefono_vendedor`,`nit_vendedor`,`estatus_vendedor`) VALUES (1,'pruebaexamen','conocida','54525905','2504587','1');
INSERT INTO aplicaciones (Aplcodigo, Aplnombre, Aplestado) VALUES (10087, 'Vendedores', '1');

-- codigo_vendedor