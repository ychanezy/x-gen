package cn.javass.xgen.output.types.outputtofile.plaintxt;

import cn.javass.xgen.output.types.outputtofile.Outter;
import cn.javass.xgen.util.file.FileHelper;

public class OutterImpl implements Outter{

	@Override
	public boolean writeContent(String outPathAndFileName, String content) {
		FileHelper.writeFile(outPathAndFileName, content);
		return true;
	}

}
