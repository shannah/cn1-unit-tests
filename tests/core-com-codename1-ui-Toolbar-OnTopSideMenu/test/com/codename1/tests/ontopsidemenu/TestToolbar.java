/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.tests.ontopsidemenu;

import com.codename1.components.ToastBar;
import com.codename1.testing.AbstractTest;
import com.codename1.testing.TestUtils;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author shannah
 */
public class TestToolbar extends AbstractTest {

    @Override
    public boolean runTest() throws Exception {
        findCommandComponent();
        testOverflowMenuNPE();
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
    
    /**
     * https://github.com/codenameone/CodenameOne/issues/2255
     */
    public void testOverflowMenuNPE() {
        Form hi = new Form();
        hi.setName("testOverflowMenuNPE");
        hi.getToolbar().addCommandToOverflowMenu("Test", FontImage.createMaterial(FontImage.MATERIAL_3D_ROTATION, new Style()), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       ToastBar.showMessage("menu", FontImage.MATERIAL_3D_ROTATION);
                       hi.revalidate();
                    }
                });
        hi.show();
        
        TestUtils.waitForFormName("testOverflowMenuNPE", 2000);
        hi.getToolbar().closeSideMenu();
        
    }
    
}
