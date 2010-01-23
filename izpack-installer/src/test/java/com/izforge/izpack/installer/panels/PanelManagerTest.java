package com.izforge.izpack.installer.panels;

import com.izforge.izpack.installer.base.IzPanel;
import com.izforge.izpack.installer.container.IInstallerContainer;
import com.izforge.izpack.installer.data.GUIInstallData;
import com.izforge.izpack.installer.panels.checkedhello.CheckedHelloPanel;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test for panel manager
 */
public class PanelManagerTest {
    @Mock
    private GUIInstallData installDataGUI;
    @Mock
    private IInstallerContainer installerContainer;
    private PanelManager panelManager;

    @Before
    public void initMock() throws ClassNotFoundException {
        MockitoAnnotations.initMocks(getClass());
        panelManager = new PanelManager(installDataGUI, installerContainer);
    }

    @Test
    public void resolveClassNameShouldAddDefaultPrefix() throws Exception {
        Class<?> aClass = panelManager.resolveClassName("HelloPanel");
        assertThat(aClass.getName(), Is.is("com.izforge.izpack.installer.panels.hello.HelloPanel"));
        aClass = panelManager.resolveClassName("FinishPanel");
        assertThat(aClass.getName(), Is.is("com.izforge.izpack.installer.panels.finish.FinishPanel"));
    }

    @Test
    public void resolveClassNameShouldNotAddPrefixWithCompleteClass() throws Exception {
        Class<?> aClass = panelManager.resolveClassName("com.izforge.izpack.installer.panels.hello.HelloPanel");
        assertThat(aClass.getName(), Is.is("com.izforge.izpack.installer.panels.hello.HelloPanel"));
        aClass = panelManager.resolveClassName("com.izforge.izpack.installer.container.InstallerContainer");
        assertThat(aClass.getName(), Is.is("com.izforge.izpack.installer.container.InstallerContainer"));
    }

    @Test
    public void shouldSearchAutomaticallyInPack() throws Exception {
        Class<? extends IzPanel> aClass = panelManager.resolveClassName("CheckedHelloPanel");
        assertThat(aClass.getName(), Is.is(CheckedHelloPanel.class.getName()));
    }


    @Test(expected = ClassNotFoundException.class)
    public void resolveClassNameShouldThrowException() throws Exception {
        panelManager.resolveClassName("unknown");
    }
}