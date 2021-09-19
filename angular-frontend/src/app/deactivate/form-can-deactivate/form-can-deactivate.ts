import {ComponentCanDeactivate} from '../can-deactivate/component-can-deactivate';
import {FormGroup, NgForm} from "@angular/forms";

export abstract class FormCanDeactivate extends ComponentCanDeactivate{

 abstract get form1():FormGroup;
 
 canDeactivate():boolean{
    //  if(this.form.value)
    //  {
    //     return true;
    //  }else{
    //     return false;
    //  }

      return !this.form1.dirty
  }
}