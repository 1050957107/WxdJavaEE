package com.example.compiler;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class MvpProcessor extends AbstractProcessor {

    ProcessorUtils mProcessorUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mProcessorUtils = new ProcessorUtils(processingEnvironment.getElementUtils(),processingEnvironment.getMessager(),processingEnvironment.getFiler());
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
       return mProcessorUtils.getSupportedAnnotationTypes();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mProcessorUtils.process(set,roundEnvironment);
        return true;
    }
}
