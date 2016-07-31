 package com.wch.guice.test.muiltinject;
 
     import com.google.inject.Binder;
     import com.google.inject.Guice;
     import com.google.inject.Inject;
     import com.google.inject.Module;
 
     /** a demo with multi interfaces
      * @author xylz (www.imxylz.cn)
      * @version $Rev: 82 $
      */
     public class MultiInterfaceServiceDemo {
         @Inject
         @Www
         private Service wwwService;
         @Inject
         @Home
         private Service homeService;
         public static void main(String[] args) {
             MultiInterfaceServiceDemo misd = Guice.createInjector(new Module() {
                 @Override
                 public void configure(Binder binder) {
                     binder.bind(Service.class).annotatedWith(Www.class).to(WwwService.class);
                     binder.bind(Service.class).annotatedWith(Home.class).to(HomeService.class);
                 }
             }).getInstance(MultiInterfaceServiceDemo.class);
             misd.homeService.execute();
             misd.wwwService.execute();
         }
     }