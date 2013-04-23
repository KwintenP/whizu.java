/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the "EUPL") version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an "AS IS" basis and 
 * without warranties of any kind concerning the Software, including 
 * without limitation merchantability, fitness for a particular purpose, 
 * absence of defects or errors, accuracy, and non-infringement of 
 * intellectual property rights other than copyright. This disclaimer 
 * of warranty is an essential part of the License and a condition for 
 * the grant of any rights to this Software.
 *   
 * For more  details, see <http://joinup.ec.europa.eu/software/page/eupl>.
 *
 * Contributors:
 *     2013 - Rudy D'hauwe @ Whizu - initial API and implementation
 *******************************************************************************/
package org.whizu.server;

import java.io.IOException;
import java.lang.annotation.Annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.whizu.annotations.AnnotationDetector;
import org.whizu.annotations.App;
import org.whizu.annotations.Autowire;
import org.whizu.ui.Application;

/**
 * @author Rudy D'hauwe
 */
class AnnotationScanner {

	private Log log = LogFactory.getLog(AnnotationScanner.class);

	public void scan(final Configuration config) {
		long start = System.currentTimeMillis();
		log.debug("Scanning classpath for annotations");

		AnnotationDetector.FieldReporter reporter2 = new AnnotationDetector.FieldReporter() {

			@SuppressWarnings("unchecked")
			@Override
			public Class<? extends Annotation>[] annotations() {
				return new Class[]{Autowire.class};
			}

			@Override
			public void reportFieldAnnotation(Class<? extends Annotation> annotation, String className, String fieldName) {
				System.out.println("@" + annotation + " in class " + className);
			}
		};

		AnnotationDetector.TypeReporter reporter = new AnnotationDetector.TypeReporter() {

			@SuppressWarnings("unchecked")
			@Override
			public Class<? extends Annotation>[] annotations() {
				return new Class[]{App.class};
			}

			@Override
			public void reportTypeAnnotation(Class<? extends Annotation> annotation, String className) {
				if (annotation.equals(App.class)) {
					log.debug("@App found in " + className);
					App ann = getClass(className).getAnnotation(App.class);
					if (ann != null) {
						config.addApplication(ann.uri(), newInstance(className));
					}
				} else {
					System.out.println("Andere @" + annotation + " in class " + className);
				}
			}

			private Class<?> getClass(String className) {
				try {
					return Class.forName(className);
				} catch (ClassNotFoundException e) {
					throw new IllegalArgumentException();
				}
			}

			private Application newInstance(String className) {
				try {
					return (Application) getClass(className).newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					throw new IllegalArgumentException();
				}
			}
		};

		final AnnotationDetector cf = new AnnotationDetector(reporter);
		try {
			cf.detect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		AnnotationDetector cf2 = new AnnotationDetector(reporter2);
		try {
			System.out.println("now detecting @Autowired");
			cf2.detect("org.whizu");
		} catch (IOException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		log.debug("first scanning took " + (end - start));
		start = System.currentTimeMillis();
		/*
		 * GenericApplicationContext applicationContext = new
		 * GenericApplicationContext(); ClassPathBeanDefinitionScanner scanner =
		 * new ClassPathBeanDefinitionScanner(applicationContext, false);
		 * scanner.addIncludeFilter(new AnnotationTypeFilter(App.class));
		 * scanner.scan("org.whizu"); applicationContext.refresh();
		 */

		// ClassPathScanningCandidateComponentProvider provider = new
		// ClassPathScanningCandidateComponentProvider(true);
		// String basePackage = "org/whizu";
		// Set<BeanDefinition> components =
		// provider.findCandidateComponents(basePackage);
		// for (BeanDefinition component : components) {
		// System.out.printf("Component: %s\n", component.getBeanClassName());
		// }
/*
		ctx = new ClassPathXmlApplicationContext("application-context.xml");
		System.out.println("BEAN " + ctx.getBean("ValueRenderer"));

		System.out.println("#apps " + ctx.getBeansWithAnnotation(App.class).size());
		end = System.currentTimeMillis();
		System.out.println("done component scanning scanning in " + (end - start) + " ms");
*/
		
		/*
		 * start = System.currentTimeMillis(); ClassPathBeanDefinitionScanner
		 * scanner = new ClassPathBeanDefinitionScanner(
		 * (BeanDefinitionRegistry) ctx.getAutowireCapableBeanFactory());
		 * scanner.addIncludeFilter(new AnnotationTypeFilter(App.class));
		 * scanner.scan("org");
		 * 
		 * System.out.println("#apps " +
		 * ctx.getBeansWithAnnotation(App.class).size()); end =
		 * System.currentTimeMillis();
		 * System.out.println("done annotation scanning in " + (end - start) +
		 * " ms");
		 * 
		 * Map<String, Object> result = ctx.getBeansWithAnnotation(App.class);
		 * for (Object o : result.values()) { System.out.println(o.getClass());
		 * App ann = o.getClass().getAnnotation(App.class); if (ann != null) {
		 * System.out.println(ann.uri()); config.addApplication(ann.uri(),
		 * (Application) o); } Annotation[] annos =
		 * o.getClass().getAnnotations(); for (Annotation a : annos)
		 * System.out.println(a); }
		 */
	}
}
