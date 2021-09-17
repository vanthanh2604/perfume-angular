import {ComponentCanDeactivate} from '../can-deactivate/component-can-deactivate';
import {FormGroup, NgForm} from "@angular/forms";

export abstract class FormCanDeactivate extends ComponentCanDeactivate{

 abstract get form():FormGroup;
 
 canDeactivate():boolean{
    //  if(this.form.value)
    //  {
    //     return true;
    //  }else{
    //     return false;
    //  }
    console.log("this.form.dirty"+!this.form.dirty)
      return !this.form.dirty
  }
}