import { Component, Directive, HostListener } from "@angular/core";
@Directive()
export abstract class ComponentCanDeactivate {
    abstract canDeactivate(): boolean;
    @HostListener('submit', ['$event.target'])
    unloadNotification($event:any) {
        if (!this.canDeactivate()) {
            console.log($event.returnValue);
            
            $event.returnValue = true;
        }
    }

    // @HostListener('window:onbeforeunload', ['$event.target'])
    //    onClick(btn: any) {
    //     console.log("fsdf");
    //   }

    // @HostListener('window:beforeunload', ['$event'])
    // unloadNotification($event: any) {
    //     if (!this.canDeactivate()) {
    //         $event.returnValue =true;
    //     }
    // }
}