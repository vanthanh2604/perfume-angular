export class ValidateService {
    static getValidatorErrorMessage(fieldName: string, validatorName: string, validatorValue?: any) {
        let config: { [key: string]: string } = {
            'required': `${fieldName} không được để trống`,
            'minlength': `${fieldName} minimum length ${validatorValue.requiredLength}`,
            'maxlength': `${fieldName} không vượt quá ${validatorValue.requiredLength} kí tự`,
            'min': `${fieldName} không thể nhỏ hơn ${validatorValue.requiredMin}`,
            'invalidPhone': `Số điện thoại không hợp lệ`,
            'invalidNumber': `${fieldName} phải là số > 0 `
        };
        const getKeyValue = <T extends object, U extends keyof T>(obj: T) => (key: U) =>
            obj[key];
        return getKeyValue(config)(validatorName);
    }

    static phoneValidator(control: any) {
        if(control.value.match()==null)
        {
            return null;
        }else{
            if (control.value.match(/^((\\+84-?)|0)?[0-9]{9}$/)) {
                return null;
            } else {
                return { 'invalidPhone': true };
            }
        }
    }

    static numberValidator(control: any) {
        if (control.value.match(/^[0-9]+$/)) {
            return null;
        } else {
            return { 'invalidNumber': true };
        }
    }
}