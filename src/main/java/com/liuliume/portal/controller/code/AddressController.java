package com.liuliume.portal.controller.code;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.MessageFormat;

/**
 * Created by clement on 8/29/15.
 */
@Controller
@RequestMapping(value={"/code/address"},method= RequestMethod.GET)
public class AddressController {

    private Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @RequestMapping(value="list",method=RequestMethod.GET)
    private ModelAndView list(ModelMap map,@SeedParam Seed<Address> seed){
        logger.info("call AddressController.list");
        try {
            addressService.list(seed);
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",
                    e.getMessage(), seed.toString()), e);
        }
        ModelAndView mav = new ModelAndView("code/list_address");
        map.put("seed", seed);
        return mav;
    }
}
