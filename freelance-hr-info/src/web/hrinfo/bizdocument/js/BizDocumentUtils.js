var BizDocumentUtils =(function($, window, document){
	
	function getUuid() {
		var s = [];
		var hexDigits = "0123456789abcdef";
		for (var i = 0; i < 36; i++) {
			s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		}
		s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
		s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
		s[8] = s[13] = s[18] = s[23] = "-";
		
		var uuid = s.join("");
		return uuid;
	}
	
	/**
	options: {
		el: input[type=file] dom节点
		loading 加载前
		progress 加载进度
		complete 计算完成
	}*/
	//代码参考自：http://blog.csdn.net/a258831020/article/details/45867191
	function calculateFileMD5(options) {
		var fileReader = new FileReader(),
	        blobSlice = File.prototype.mozSlice || File.prototype.webkitSlice || File.prototype.slice,
	        file = options.file,
	        chunkSize = 2097152,
	        chunks = Math.ceil(file.size / chunkSize),
	        currentChunk = 0,
	        spark = new SparkMD5();
		
	    if (options.loading) {
			options.loading.call(options.scope||window, file.name, file, options);
		}
		
	    fileReader.onload = function(e) {
	        if (options.progress) {
				options.progress.call(options.scope||window, currentChunk + 1, chunks, file.name, file, options);
			}
	        spark.appendBinary(e.target.result); // append binary string
	        currentChunk++;

	        if (currentChunk < chunks) {
	            loadNext();
	        }
	        else {
	        	var md5 = spark.end();
	            if (options.complete) {
					options.complete.call(options.scope||window, md5, file.name, file, options);
				}
	        }
	    };

	    function loadNext() {
	        var start = currentChunk * chunkSize,
	            end = start + chunkSize >= file.size ? file.size : start + chunkSize;

	        fileReader.readAsBinaryString(blobSlice.call(file, start, end));
	    };

	    loadNext();
	}

	return {
		uuid: getUuid,
		// 异步执行函数，需要在回调函数里面进行下一步操作
		md5: calculateFileMD5
	}
	
})(jQuery, window, document);