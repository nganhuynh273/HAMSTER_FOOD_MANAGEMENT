class App {
    static DOMAIN = location.origin;

    static BASE_URL_MATERIAL = this.DOMAIN + "/api/materials";
    static BASE_URL_ROLE = this.DOMAIN + "/api/roles";
    static BASE_URL_PRODUCT_TYPE = this.DOMAIN + "/api/productTypes";
    static BASE_URL_AUTH = this.DOMAIN + "/api/auth";
    static BASE_URL_USER = this.DOMAIN + "/api/users";

    static SUCCESS_CREATED = "Tạo dữ liệu thành công!";
    static SUCCESS_UPDATED = "Cập nhật dữ liệu thành công!";
    static SUCCESS_REMOVED = "Xóa thành công!";

    static REMOVE_MATERIAL = "Bạn chắc chắn muốn xóa loại thức ăn này không?";
    static BLOCK_USER = 'Bạn chắc chắn muốn chặn người dùng này không?';

    static SweetAlert = class {
        static showConfirmDialog(message) {
            return Swal.fire({
                icon: 'WARNING',
                text: message,
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, please!',
                cancelButtonText: 'Cancel',
            })
        }

        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
            })
        }
    }


    static IziToast = class {
        static showSuccessAlert(t) {
            iziToast.success({
                title: 'OK',
                position: 'topRight',
                timeout: 5000,
                message: t
            });
        }

        static showErrorAlert(t) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: false,
                message: t
            });
        }
    }
}

class Material{
    constructor(id, materialName, materialContent, quantity, productType, materialUsage, pricePerUnit, productionDate, expirationDate, note) {
        this.id = id;
        this.materialName = materialName;
        this.materialContent = materialContent;
        this.quantity = quantity;
        this.productType = productType;
        this.materialUsage = materialUsage;
        this.pricePerUnit = pricePerUnit;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
        this.note = note;
    }
}

class ProductType {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
}