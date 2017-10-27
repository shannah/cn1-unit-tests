/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.tests.ontopsidemenu;

import com.codename1.testing.AbstractTest;
import com.codename1.testing.TestUtils;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author shannah
 */
public class TestToolbar extends AbstractTest {

    @Override
    public boolean runTest() throws Exception {
        findCommandComponent();
        return true;
    }
    
    private void findCommandComponent() {
        Form f = new Form();
        Toolbar tb = new Toolbar();
        f.setToolbar(tb);
        Command cmd = tb.addMaterialCommandToLeftBar(null, FontImage.MATERIAL_3D_ROTATION, 4.5f, e-> {
            
        });
        
        Component res = tb.findCommandComponent(cmd);
        
        TestUtils.assertTrue(res != null, "Could not find command component added to left bar.");
        
    }
    
}
