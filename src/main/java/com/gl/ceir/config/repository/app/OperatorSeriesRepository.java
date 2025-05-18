/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gl.ceir.config.repository.app;


import com.gl.ceir.config.model.app.OperatorSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperatorSeriesRepository extends JpaRepository<OperatorSeries, Long>, JpaSpecificationExecutor<OperatorSeries> {

    public OperatorSeries findBySeriesStartLessThanEqualAndSeriesEndGreaterThanEqual(int SeriesStart, int SeriesEnd);

}
