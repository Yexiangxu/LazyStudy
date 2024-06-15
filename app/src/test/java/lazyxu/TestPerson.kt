package lazyxu

class TestPerson(builder: Builder) {
     var mPersonName= builder.mPersonName
     var mPersonAge =builder.mPersonAge
     var mSex = builder.mSex
     var mCardNumber=builder.mCardNumber


    class Builder {
        internal var mPersonName: String? = null
        internal var mPersonAge = 0
        internal var mSex = 0
        internal var mCardNumber: String? = null
        fun build(): TestPerson {
            return TestPerson(this)
        }

        fun addPersonName(mPersonName: String?): Builder {
            this.mPersonName = mPersonName
            return this
        }

        fun addPersonAge(mPersonAge: Int): Builder {
            this.mPersonAge = mPersonAge
            return this
        }

        fun addSex(mSex: Int): Builder {
            this.mSex = mSex
            return this
        }

        fun addCardNumber(mCardNumber: String?): Builder {
            this.mCardNumber = mCardNumber
            return this
        }
    }


}
