/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gl.ceir.config.repository.app;


import com.gl.ceir.config.model.app.OperatorSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperatorSeriesRepository extends JpaRepository<OperatorSeries, Long>, JpaSpecificationExecutor<OperatorSeries> {

//    @Query(value = "select operator_name from  operator_series where  ( series_start=:msisdn  and series_type = 'msisdn') or ( series_start=:imsi and series_type = 'imsi') limit 1 ", nativeQuery = true)
//    public String getOperatorNameFromMsisdnAndImsi(String msisdn, String imsi);

    public OperatorSeries getBySeriesStartAndSeriesType(int SeriesStart, String SeriesType);
// // select OPERATOR_NAME from OPERATOR_SERIES where SERIES_START <= '85514' and SERIES_END >= '85514';
    public OperatorSeries  findBySeriesStartLessThanEqualAndSeriesEndGreaterThanEqual(int SeriesStart, int SeriesEnd);

}
