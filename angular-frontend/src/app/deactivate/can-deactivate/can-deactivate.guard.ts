import { Injectable } from '@angular/core';
import { CanDeactivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ConfirmBoxSevice } from 'src/app/service/confirmBox/confirmBox.service';
import { ComponentCanDeactivate } from './component-can-deactivate';
import Swal from 'sweetalert2';
import { async } from 'rxjs';
@Injectable()
export class CanDeactivateGuard implements CanDeactivate<ComponentCanDeactivate> {
  confirmBoxSevice = new ConfirmBoxSevice()
  confirmBox = this.confirmBoxSevice.confirmBoxWarning();
 
  async canDeactivate(component: ComponentCanDeactivate) {
    let a = true;
    if (!component.canDeactivate()) {
 
      const b = await this.confirmBox.openConfirmBox$().toPromise().then(resp => {
        a = resp.Success;
        return resp.Success;
      }, reason => {
        console.log(reason);
      })
    }
    return a;
  }
}