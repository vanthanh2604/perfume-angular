import { ConfirmBoxInitializer, DialogLayoutDisplay } from "@costlydeveloper/ngx-awesome-popup";
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })

export class ConfirmBoxSevice {
    confirmBoxDelete() {
        const confirmBox = new ConfirmBoxInitializer();
        confirmBox.setTitle('Bạn có chắc không?');
        confirmBox.setMessage('Xác nhận xóa sản phẩm?');
        confirmBox.setButtonLabels('Có', 'Không');
        confirmBox.setConfig({
            LayoutType: DialogLayoutDisplay.DANGER // SUCCESS | INFO | NONE | DANGER | WARNING
        });
        return confirmBox
    }

    confirmBoxDeleteLoad() {
        const confirmBox = new ConfirmBoxInitializer();
        confirmBox.setTitle('Sản phẩm đã được người khác xóa!');
        confirmBox.setMessage('Bạn có muốn load lại trang?');
        confirmBox.setButtonLabels('Có', 'Không');
        confirmBox.setConfig({
            LayoutType: DialogLayoutDisplay.WARNING // SUCCESS | INFO | NONE | DANGER | WARNING
        });
        return confirmBox
    }
    confirmBoxUpdate(){
        const confirmBox = new ConfirmBoxInitializer();
        confirmBox.setMessage('Bạn có muốn tiếp tục?');
        confirmBox.setButtonLabels('Có', 'Không');
        confirmBox.setConfig({
            LayoutType: DialogLayoutDisplay.WARNING // SUCCESS | INFO | NONE | DANGER | WARNING
        });
        return confirmBox
    }
    confirmBoxOutput(){
        const confirmBox = new ConfirmBoxInitializer();
        confirmBox.setTitle('Giá bán hiện tại nhỏ hơn giá nhập');
        confirmBox.setMessage('Bạn có muốn tiếp tục?');
        confirmBox.setButtonLabels('Có', 'Không');
        confirmBox.setConfig({
            LayoutType: DialogLayoutDisplay.WARNING // SUCCESS | INFO | NONE | DANGER | WARNING
        });
        return confirmBox
    }
    confirmBoxMsg(){
        const confirmBox = new ConfirmBoxInitializer();
        confirmBox.setTitle('Không tìm thấy sản phẩm này!');
        confirmBox.setMessage('Bạn có muốn tiếp tục?');
        confirmBox.setButtonLabels('Có', 'Không');
        confirmBox.setConfig({
            LayoutType: DialogLayoutDisplay.WARNING // SUCCESS | INFO | NONE | DANGER | WARNING
        });
        return confirmBox
    }
}