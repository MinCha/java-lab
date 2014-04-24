package com.googlecode.goodsamples.lab;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTest {
	OutputStreamWriter output = new OutputStreamWriter(System.out);

	@Test
	public void freemarker() throws TemplateException, IOException {
		Map<String, String> datamodel = new HashMap<String, String>();
		datamodel.put("number", "6");
		FakeModel fakeModel = new FakeModel();
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(this.getClass(), "/");
		Template tpl = cfg.getTemplate("freemarker.fm");
		tpl.process(new FakeModel(), output);
	}
}
