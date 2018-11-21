
/**
 * 验证订单数量格式
 */
function checkOrderNumber(v) {
	var reg = new RegExp("^[1-9][0-9]*$"); 
	return reg.test(v);
}

/**
 * 验证厂商编号为四位长度16进制数字
 */
function checkFactoryCode(v) {
	var reg = /^[0-9A-F]{4}$/;
	return reg.test(v.toUpperCase());
}

/**
 * 验证物料版本号格式
 */
function checkVersionCode(v) {
	var reg = /^V[0-9]\.[0-9]\.[0-9]$/;
	return reg.test(v.toUpperCase());
}

