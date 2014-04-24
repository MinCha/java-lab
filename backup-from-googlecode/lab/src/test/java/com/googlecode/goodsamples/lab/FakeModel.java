package com.googlecode.goodsamples.lab;

import java.util.List;

import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;

public class FakeModel implements TemplateScalarModel, TemplateBooleanModel,
		TemplateHashModel, TemplateSequenceModel, TemplateMethodModel {
	private final String text;

	public FakeModel() {
		this("");
	}

	public FakeModel(String text) {
		this.text = text;
	}

	public String getAsString() {
		return "${" + text + "}";
	}

	public boolean getAsBoolean() {
		return true;
	}

	public TemplateModel get(String key) {
		return new FakeModel(text + "." + key);
	}

	public TemplateModel get(int index) {
		return new FakeModel(text + "[" + index + "]");
	}

	public boolean isEmpty() {
		return false;
	}

	public int size() {
		return 1;
	}

	public TemplateModel exec(List args) throws TemplateModelException {
		return new FakeModel(text
				+ args.toString().replace('[', '(').replace(']', ')'));
	}
}