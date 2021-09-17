import { Injectable } from '@angular/core';
import { CanDeactivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ConfirmBoxSevice } from 'src/app/service/confirmBox/confirmBox.service';
import { ComponentCanDeactivate } from './component-can-deactivate';
import Swal from 'sweetalert2';
import { async } from 'rxjs';
@Injectable()
export class CanDeactivateGuard implements CanDeactivate<ComponentCanDeactivate> {
  confirmBoxSevice = new ConfirmBoxSevice()
  confirmBox = this.confirmBoxSevice.confirmBoxUpdate();
  check: Boolean

  a() {
    return this.confirmBox.openConfirmBox$().subscribe(resp => {
      console.log(123);
      return resp.Success;
    })
    // return true
  }

  async canDeactivate(component: ComponentCanDeactivate) {

    let a = false;
    if (!component.canDeactivate()) {

      const b = await this.confirmBox.openConfirmBox$().subscribe(resp => {
        a = resp.Success;
        return resp.Success;
      })
      
      console.log(b);
      // console.log(a['isStopped'])
      // if (confirm("Nếu bạn chuyển hướng thì dữ liệu vừa nhập sẽ bị mất bạn có muốn tiếp tục!")) {
      //   console.log("có")
      //   return true;
        
      // } else {
      //   console.log("không")
      //   return false;
      // }
    }
    // Swal.fire({
    //   position: 'top',
    //   title: 'test',
    //   // text: "You won't be able to revert this!",
    //   icon: 'question',
    //   showCancelButton: true,
    //   confirmButtonColor: '#3085d6',
    //   cancelButtonColor: '#d33',
    //   cancelButtonText: 'Trở về',
    //   confirmButtonText: 'Xác nhận'
    // }).then((result) => {
    //   if (result.isConfirmed) {
    //     console.log(result.isConfirmed)
    //     window.location.reload();
    //   }
    // })
    
    return a;
  }
}
