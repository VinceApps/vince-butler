package no.vince.butler.module.m3database.controller;

import com.ibm.as400.access.ConnectionPoolException;
import no.vince.butler.module.m3database.service.M3DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
public class M3DatabaseController
{
    @Autowired
    private M3DatabaseService m3DatabaseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Map<String, Object>> executeQuery(@RequestParam String sqlQuery, @RequestParam(required = false) Object... params) throws ConnectionPoolException, SQLException
    {
        return m3DatabaseService.executeQuery(sqlQuery, params);
    }
}
