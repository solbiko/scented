package com.dysb.scented.model;

import com.dysb.scented.exception.ApiRequestException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiPageParameter {

    private Integer page;
    private Integer size;
    private Sort sort;

    public ApiPageParameter(){
        this.page = 0;
        this.size = 5;
        List<Sort.Order> orders = new ArrayList<>();
        this.sort = Sort.by(orders);
    }

    private void parseServletRequest(HttpServletRequest request) throws ApiRequestException {
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        String sortField = request.getParameter("sort");
        String dir  = request.getParameter("dir");

        try {
            if(size!=null && !size.equals(""))
                this.size = Integer.valueOf(size);
            if(size==null || this.size==null || this.size < 1)
                this.size = 5;
        }
        catch (RuntimeException e){
            throw new ApiRequestException("'size'", e);
        }

        try {
            if(page==null || page.equals(""))
                this.page = 0;
            else
                this.page = Integer.valueOf(page);
        }
        catch (NumberFormatException e){
            throw new ApiRequestException("'page'", e);
        }

        List<Sort.Order> orders = new ArrayList<>();
        if(sortField!=null && !sortField.equals("")) {
            String[] fields = sortField.split(",");
            for (String f : fields) {
                Sort.Order o = new Sort.Order(
                        dir == null || !dir.equals("desc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                        f);
                orders.add(o);
            }
        }
        this.sort = Sort.by(orders);
    }
    public ApiPageParameter(HttpServletRequest request) throws ApiRequestException {
        parseServletRequest(request);
    }

    public ApiPageParameter(RequestAttributes requestAttributes) throws ApiRequestException {
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        parseServletRequest(request);
    }
    public PageRequest to(){
        return PageRequest.of(this.page, this.size, this.sort);
    }
}
