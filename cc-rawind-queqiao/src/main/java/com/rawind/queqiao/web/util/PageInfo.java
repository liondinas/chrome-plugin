package com.rawind.queqiao.web.util;

import java.util.ArrayList;
import java.util.List;

public class PageInfo<T> {
	public static final int DEFAULT_LIMIT = 20;

	private List<T> dataList;

	private int totalRecord;

	private int firstPageNo;

	private int nextPageNo;

	private int lastPageNo;

	private int pageNo;

	private int limit = DEFAULT_LIMIT;

	private int offset = 0;

	private int pageCount;

	/**
	 * 页码显示数量
	 */
	private static final int INT_PAGE_NO_SIZE = 3;

	public int getOffset() {
		return offset;
	}

	public void init(int totalRecord, final int pageNo, int limit) {
		setTotalRecord(totalRecord);

		if (limit > 0) {
			setLimit(limit);
		} else {
			setLimit(DEFAULT_LIMIT);
			limit = DEFAULT_LIMIT;
		}

		if (totalRecord % limit == 0) {
			// 如果没有记录，那就最后一页就是第一页
			if (totalRecord == 0) {
				setLastPageNo(1);
			} else {
				setLastPageNo(totalRecord / limit);
			}
		} else {
			setLastPageNo(totalRecord / limit + 1);
		}
		int pageNum = pageNo > lastPageNo ? lastPageNo : pageNo;
		pageNum = pageNum <= 0 ? 1 : pageNum;
		setPageNo(pageNum);

		if (pageNum > 1) {
			setFirstPageNo(pageNum - 1);
		} else {
			setFirstPageNo(1);
		}

		if (getLastPageNo() > pageNum) {
			setNextPageNo(pageNum + 1);
		} else {
			setNextPageNo(pageNum);
		}
		if (totalRecord == 0) {
			offset = 0;
		} else {
			offset = (getPageNo() - 1) * limit;
		}
	}

	public PageInfo(int totalRecord, int pageNo, int limit, List<T> dataList) {
		setDataList(dataList);
		init(totalRecord, pageNo, limit);
	}

	public PageInfo(int pageNo, int limit) {
		this.pageNo = pageNo;
		this.limit = limit;
		if (pageNo > 0) {
			this.offset = (pageNo - 1) * limit;
		}
	}

	public PageInfo(int totalRecord, int pageNo, int limit) {
		init(totalRecord, pageNo, limit);
	}

	/**
	 * 通过全部的记录构造正确的pageinfo对象
	 * 
	 * @param pageNo
	 *            页号
	 * @param limit
	 *            每页多少条记录
	 * @param totalData
	 *            总记录
	 */
	public PageInfo(int pageNo, int limit, List<T> totalData) {
		int totalSize = totalData.size();
		init(totalSize, pageNo, limit);

		int begin = getOffset();
		int end = begin + limit > totalSize ? totalSize : (begin + limit);
		setDataList(totalData.subList(begin, end));
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public int getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo < lastPageNo ? nextPageNo : lastPageNo;
	}

	public int getPrePageNo() {
		int page = pageNo - 1;
		page = page > 0 ? page : 1;
		return page;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getLastPageNo() {
		return lastPageNo;
	}

	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * 是否含有下一页
	 * 
	 * @return
	 */
	public boolean hasNextPage() {
		return pageNo != lastPageNo;
	}

	/**
	 * 是否含有上一页
	 * 
	 * @return
	 */
	public boolean hasPrePage() {
		return pageNo != 1;
	}

	/**
	 * 获得页码列表
	 * 
	 * @return
	 */
	public List<Integer> getPageNumList() {
		List<Integer> pageNoList = new ArrayList<Integer>();
		if (hasPrePage() && hasNextPage() && getPageNo() > 1) {
			assignPageNo(getPageNo() - INT_PAGE_NO_SIZE / 2, pageNoList);
		} else if (!hasPrePage()) {
			assignPageNo(getPageNo(), pageNoList);
		} else if (!hasNextPage()) {
			assignPageNo(getPageNo() - (INT_PAGE_NO_SIZE - 1), pageNoList);
		}
		return pageNoList;
	}

	/**
	 * 给页码赋值
	 * 
	 * @param start
	 * @param pageNoList
	 */
	private void assignPageNo(final int start, List<Integer> pageNoList) {
		int startPageNo = start;
		int loopCount = getLastPageNo() > INT_PAGE_NO_SIZE ? INT_PAGE_NO_SIZE
				: getLastPageNo();
		startPageNo = startPageNo > 0 ? startPageNo : 1;
		for (int i = 0; i < loopCount; i++) {
			pageNoList.add(startPageNo);
			startPageNo = startPageNo + 1;
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
