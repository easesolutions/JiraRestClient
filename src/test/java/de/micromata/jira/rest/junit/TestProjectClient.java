package de.micromata.jira.rest.junit;

import de.micromata.jira.rest.core.domain.ComponentBean;
import de.micromata.jira.rest.core.domain.ProjectBean;
import de.micromata.jira.rest.core.domain.VersionBean;
import de.micromata.jira.rest.core.domain.meta.MetaBean;
import de.micromata.jira.rest.core.util.RestException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User: Christian Schulze
 * Email: c.schulze@micromata.de
 * Date: 10.08.2014
 */
public class TestProjectClient extends BaseTest {


    @Test
    public void testGetProjectByKey() throws RestException, IOException, ExecutionException, InterruptedException {
        final Future<ProjectBean> future = jiraRestClient.getProjectClient().getProjectByKey(projectKeyToSearch);
        final ProjectBean project = future.get();
        Assert.assertNotNull(project);
        Assert.assertEquals(projectKeyToSearch, project.getKey());
    }

    @Test
    public void testGetAllProjects() throws RestException, IOException, ExecutionException, InterruptedException {
        final Future<List<ProjectBean>> future = jiraRestClient.getProjectClient().getAllProjects();
        final List<ProjectBean> projectBeans = future.get();
        Assert.assertNotNull(projectBeans);
        Assert.assertFalse(projectBeans.isEmpty());
    }

    @Test
    public void testGetProjectVersions() throws RestException, IOException, ExecutionException, InterruptedException {
        final Future<List<VersionBean>> future = jiraRestClient.getProjectClient().getProjectVersions(projectKeyToSearch);
        final List<VersionBean> versionBeans = future.get();
        Assert.assertNotNull(versionBeans);
        Assert.assertFalse(versionBeans.isEmpty());
    }

    @Test
    public void testGetProjectComponents() throws RestException, IOException, ExecutionException, InterruptedException {
        final Future<List<ComponentBean>> future = jiraRestClient.getProjectClient().getProjectComponents(projectKeyToSearch);
        final List<ComponentBean> componentBeans = future.get();
        Assert.assertNotNull(componentBeans);
        Assert.assertFalse(componentBeans.isEmpty());
    }

    @Test
    @Ignore("This method is deprecated in JIRA")
    public void testGetIssueTypesMetaForProject() throws ExecutionException, InterruptedException {
        Future<MetaBean> future = jiraRestClient.getProjectClient().getIssueTypesMetaForProject(projectKeyToSearch);
        MetaBean metaBean = future.get();
        Assert.assertNotNull(metaBean);
    }
}
