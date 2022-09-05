
$(function () {
    page.dialogs.element.frmCreMaterial.validate({
        onkeyup: function(element) { $(element).valid() },
        onclick: false,
        onfocusout: false,
        rules: {
            materialNameCre: {
                required: true,
                maxlength: 100
            },
            materialContentCre: {
                required: true,
                number: true,
                min: 0,
                max: 10000000
            },
            quantityCre: {
                required: true,
                number: true,
                min: 0,
                max: 5000000
            },
            pricePerUnitCre: {
                required: true,
                number: true,
                min: 0,
                max: 5000000
            },
            productTypeCre: {
                required: true,
                number: true,
                min: 0,
            },
            productionDateCre: {
                required: true
            },
            expirationDateCre: {
                required: true
            },

        },
        errorLabelContainer: "#mdCreateMaterial .modal-alert-danger",
        errorPlacement: function (error, element) {
            error.appendTo("#mdCreateMaterial .modal-alert-danger");
        },
        showErrors: function(errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                page.dialogs.alertDanger.mdCreateMaterial.removeClass("hide").addClass("show");
            } else {
                page.dialogs.alertDanger.mdCreateMaterial.removeClass("show").addClass("hide").empty();
                page.dialogs.element.frmCreMaterial.removeClass("error");
            }
            this.defaultShowErrors();
        },
        messages: {
            materialNameCre: {
                required: "Tên thức ăn không được để trống.",
                maxlength: $.validator.format("Các ký tự tối đa của tên thức ăn là {0}.")
            },
            materialContentCre: {
                required: "Hàm lượng dinh dưỡng thức ăn không được để trống.",
                number: "Hàm lượng dinh dưỡng phải là chữ số.",
                min: "Hàm lượng dinh dưỡng thức ăn phải lớn hơn 0.",
                max: $.validator.format("Giá trị tối đa hàm lượng dinh dưỡng thức ăn là {0}.")
            },
            quantityCre: {
                required: "Số lượng không được để trống.",
                number: "Số lượng phải là chữ số.",
                min: "Số lượng phải lớn hơn 0.",
                max: $.validator.format("Giá trị tối đa của số lượng là {0}.")
            },
            pricePerUnitCre: {
                required: "Giá không được để trống.",
                number: "Giá thức ăn phải là chữ số.",
                min: "Giá thức ăn phải lớn hơn 0.",
                max: $.validator.format("Giá trị tối đa của giá là {0}.")
            },
            productTypeCre: {
                required: "ID loại thức ăn không được để trống.",
                number: "ID loại thức ăn phải là chữ số.",
                min: "ID loại thức ăn phải lớn hơn 0."
            },
            productionDateCre: {
                required: "Ngày sản xuất không được để trống.",
            },
            expirationDateCre: {
                required: "Ngày hết hạn không được để trống.",
            },
        },
        submitHandler: function() {
            page.dialogs.commands.createNewMaterial();
        }
    });

    page.dialogs.element.frmUpMaterial.validate({
        onkeyup: function(element) { $(element).valid() },
        onclick: false,
        onfocusout: false,
        rules: {
            materialNameUp: {
                required: true,
                maxlength: 100
            },
            materialContentUp: {
                required: true,
                number: true,
                min: 0,
                max: 10000000
            },
            quantityUp: {
                required: true,
                number: true,
                min: 0,
                max: 5000000
            },
            pricePerUnitUp: {
                required: true,
                number: true,
                min: 0,
                max: 5000000
            },
            productTypeUp: {
                required: true,
                number: true,
                min: 0,
            },
            productionDateUp: {
                required: true
            },
            expirationDateUp: {
                required: true
            },

        },
        errorLabelContainer: "#mdUpdateMaterial .modal-alert-danger",
        errorPlacement: function (error, element) {
            error.appendTo("#mdUpdateMaterial.modal-alert-danger");
        },
        showErrors: function(errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                page.dialogs.alertDanger.mdUpdateMaterial.removeClass("hide").addClass("show");
            } else {
                page.dialogs.alertDanger.mdUpdateMaterial.removeClass("show").addClass("hide").empty();
                page.dialogs.element.frmUpMaterial.removeClass("error");
            }
            this.defaultShowErrors();
        },
        messages: {
            materialNameUp: {
                required: "Tên thức ăn không được để trống.",
                maxlength: $.validator.format("Các ký tự tối đa của tên thức ăn là {0}.")
            },
            materialContentUp: {
                required: "Hàm lượng dinh dưỡng thức ăn không được để trống.",
                number: "Hàm lượng dinh dưỡng phải là chữ số.",
                min: "Hàm lượng dinh dưỡng thức ăn phải lớn hơn 0.",
                max: $.validator.format("Giá trị tối đa hàm lượng dinh dưỡng thức ăn là {0}.")
            },
            quantityUp: {
                required: "Số lượng không được để trống.",
                number: "Số lượng phải là chữ số.",
                min: "Số lượng phải lớn hơn 0.",
                max: $.validator.format("Giá trị tối đa của số lượng là {0}.")
            },
            pricePerUnitUp: {
                required: "Giá không được để trống.",
                number: "Giá thức ăn phải là chữ số.",
                min: "Giá thức ăn phải lớn hơn 0.",
                max: $.validator.format("Giá trị tối đa của giá là {0}.")
            },
            productTypeUp: {
                required: "ID loại thức ăn không được để trống.",
                number: "ID loại thức ăn phải là chữ số.",
                min: "ID loại thức ăn phải lớn hơn 0."
            },
            productionDateUp: {
                required: "Ngày sản xuất không được để trống.",
            },
            expirationDateUp: {
                required: "Ngày hết hạn không được để trống.",
            },
        },
        submitHandler: function() {
            page.dialogs.commands.updateMaterial();
        }
    });
});