package com.dysb.scented.model;

import com.dysb.scented.enumerate.ErrorMessage;
import com.dysb.scented.exception.ApiRequestException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class ApiQueryParameter {

    private final HashMap<String, String> para;

    private void parseServletRequest(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if(queryString == null)
            return;
        String[] queryArray = queryString.split("&");
        //if(queryArray!=null){
            for(String q : queryArray){
                String[] kv = q.split("=");
                if(/*kv!=null && */kv.length==2) {
                    try {
                        para.put(kv[0], URLDecoder.decode(kv[1], StandardCharsets.UTF_8.toString()));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        //}
    }

    public ApiQueryParameter(HttpServletRequest request) {
        para = new HashMap<>();
        parseServletRequest(request);
    }

    public ApiQueryParameter(RequestAttributes requestAttributes) {
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        para = new HashMap<>();
        parseServletRequest(request);
    }

    public ApiQueryParameter(){
        para = new HashMap<>();
    }

    public void addParaLong(String k, Long v){
        para.put(k,String.valueOf(v));
    }

    public void addParaObject(String k, Object v){
        if(v==null)
            para.put(k,null);
        else
            para.put(k,String.valueOf(v));
    }

    public void addParaQuery(String k, Object v) {
        if (v != null) {
            para.put(k, String.valueOf(v));
        }
    }

    public Integer getParaInt(String k){
        String v = para.get(k);
        Integer r = null;
        try{
            if(v != null)
                r = Integer.valueOf(v);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            throw new ApiRequestException(ErrorMessage.ERROR_INVALID_PARAMETER.getMessage());
        }
        return r;
    }

    public Long getParaLong(String k){
        String v = para.get(k);
        long r;
        try{
            if(v==null)
                return null;
            r = Long.parseLong(v);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            throw new ApiRequestException(ErrorMessage.ERROR_INVALID_PARAMETER.getMessage());
        }
        return r;
    }

    public Boolean getParaBool(String k){
        String v = para.get(k);
        boolean r;
        try{
            if(v==null)
                return null;
            r = v.equalsIgnoreCase("true") || v.equals("1");
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            throw new ApiRequestException(ErrorMessage.ERROR_INVALID_PARAMETER.getMessage());
        }
        return r;
    }

    public LocalDateTime getParaDateTime(String k){
        String v = para.get(k);
        LocalDateTime r;
        try{
            if(v==null)
                return null;
            r = LocalDateTime.parse(v);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            throw new ApiRequestException(ErrorMessage.ERROR_INVALID_PARAMETER.getMessage());
        }
        return r;
    }

    public LocalDate getParaDate(String k){
        String v = para.get(k);
        LocalDate r;
        try{
            if(v==null)
                return null;
            r = LocalDate.parse(v);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            throw new ApiRequestException(ErrorMessage.ERROR_INVALID_PARAMETER.getMessage());
        }
        return r;
    }

    public List<LocalDate> getParaDates(String k){
        List<LocalDate> list = new ArrayList<>();
        if(para.get(k)==null)
            return list;
        Arrays.stream(para.get(k).split(",")).forEach( d -> {
            LocalDate r;
            try{
                r = LocalDate.parse(d);
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
                throw new ApiRequestException(ErrorMessage.ERROR_INVALID_PARAMETER.getMessage());
            }
            list.add(r);
        });
        return list;
    }

    public YearMonth getParaYearMonth(String k){
        String v = para.get(k);
        YearMonth r;
        try{
            if(v==null)
                return null;
            r = YearMonth.parse(v);
        }
        catch (DateTimeParseException ex){
            ex.printStackTrace();
            throw new ApiRequestException(ErrorMessage.ERROR_INVALID_PARAMETER.getMessage());
        }
        return r;
    }

    public Year getParaYear(String k){
        String v = para.get(k);
        Year r;
        try{
            if(v==null)
                return null;
            r = Year.parse(v);
        }
        catch (DateTimeParseException ex){
            ex.printStackTrace();
            throw new ApiRequestException(ErrorMessage.ERROR_INVALID_PARAMETER.getMessage());
        }
        return r;
    }

    public LocalTime getParaTime(String k){
        String v = para.get(k);
        LocalTime r;
        try{
            if(v==null)
                return null;
            r = LocalTime.parse(v);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            throw new ApiRequestException(ErrorMessage.ERROR_INVALID_PARAMETER.getMessage());
        }
        return r;
    }

    public Instant getParaInstant(String k){
        String v = para.get(k);
        Instant r;
        try{
            if(v==null)
                return null;
            Date date = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").parse(v);
            r = date.toInstant();
            //r = LocalDateTime.parse(v);
        }
        catch (NumberFormatException | ParseException ex){
            ex.printStackTrace();
            throw new ApiRequestException(ErrorMessage.ERROR_INVALID_PARAMETER.getMessage());
        }
        return r;
    }

    public String getParaStr(String k){
        return para.get(k);
    }

    public List<String> getParaStrList(String k){
        String p = para.get(k);
        if(p!=null) {
            String[] ps = p.split(",");
            return Arrays.stream(ps).toList();
        }
        return null;
    }

    public String getParaStrLowerCase(String k){
        if(para.get(k)==null)
            return null;
        return para.get(k).toLowerCase();
    }

    public String toString(){
        AtomicReference<String> r = new AtomicReference<>("");
        para.forEach((k,v) -> r.set(r.get() + k + ":" + v + " "));
        return r.get();
    }

    public void delPara(String k){
        para.remove(k);
    }

    public List<Long> getParaLongList(String k){
        String p = para.get(k);
        if(p!=null) {
            String[] ps = p.split(",");
            return Arrays.stream(ps).map(Long::parseLong).toList();
        }
        return null;
    }

    public List<Integer> getParaIntList(String k){
        String p = para.get(k);
        if(p!=null) {
            String[] ps = p.split(",");
            return Arrays.stream(ps).map(Integer::parseInt).toList();
        }
        return null;
    }
}
