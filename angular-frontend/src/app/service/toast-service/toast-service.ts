import { Injectable, TemplateRef } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ToastService {
  toasts: any[] = [];

  show(textOrTpl: string | TemplateRef<any>, options: any = {}) {
    let msg_success1: string | TemplateRef<any>
    msg_success1="Thành công!"
    const msg_fail="Thất bại!"
    const options_success={classname: 'bg-success text-light', delay: 4000}
    const options_fail={classname: 'bg-danger text-light', delay: 4000}
    if(textOrTpl=="success")
    {
      // this.toasts.push({ msg_success1, ...options_success });
      this.success("Thành công!")
    }
    else
    {
      // this.toasts.push({ msg_fail, ...options_fail });
      this.fail("Thất bại!")
    }
    // this.toasts.push({ textOrTpl, ...options });
  }

  success(textOrTpl: string | TemplateRef<any>) {
    const options_success={classname: 'bg-success text-light', delay: 4000}
    this.toasts.push({ textOrTpl, ...options_success });
  }
  fail(textOrTpl: string | TemplateRef<any>) {
    const options_fail={classname: 'bg-danger text-light', delay: 4000}
    this.toasts.push({ textOrTpl, ...options_fail });
  }
  warning(textOrTpl: string | TemplateRef<any>) {
    const options_fail={classname: 'bg-danger text-light', delay: 4000}
    this.toasts.push({ textOrTpl, ...options_fail });
  }
  remove(toast: any) {
    this.toasts = this.toasts.filter(t => t !== toast);
  }
}
